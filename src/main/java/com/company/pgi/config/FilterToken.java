package com.company.pgi.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.company.pgi.repository.IUsersRepository;
import com.company.pgi.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterToken extends OncePerRequestFilter{

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IUsersRepository usersRepository;

    @Override
    protected void doFilterInternal( @SuppressWarnings("null") HttpServletRequest request, @SuppressWarnings("null") HttpServletResponse response,
        @SuppressWarnings("null") FilterChain filterChain) throws IOException, ServletException {
        
        String token;

        var authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader != null){
            token = authorizationHeader.replace("Bearer ", "");
            var subject = this.tokenService.getSubject(token);

            var users = this.usersRepository.findByLogin(subject);

            var authentication = new UsernamePasswordAuthenticationToken( users, null,  users.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        filterChain.doFilter(request, response);
    
    }

}
