package com.company.pgi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.pgi.dto.LoginDto;
import com.company.pgi.dto.TokenDto;
import com.company.pgi.dto.responseBase.ResponseBase;
import com.company.pgi.model.Users;
import com.company.pgi.service.TokenService;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<ResponseBase<TokenDto>> login(@RequestBody LoginDto login) {
        try {

            // Cria o token de autenticação com as credenciais fornecidas
            Authentication authenticationToken =
            UsernamePasswordAuthenticationToken.unauthenticated(login.login(), login.password());
            
            // Executa a autenticação
            Authentication authenticated = authenticationManager.authenticate(authenticationToken);
            
            // Obtém o usuário autenticado
            Users user = (Users) authenticated.getPrincipal();
            
            // Gera o token para o usuário autenticado
            String token = tokenService.gerarToken(user);

            TokenDto tDto = new TokenDto();
            tDto.setAccessToken(token);

            ResponseBase<TokenDto> response = new ResponseBase<>();
            response.setStatusCode("200");
            response.setMessage("ok");
            response.setData(tDto);
            response.setVersion("v1.0.0");
            
            // Retorna o token como resposta HTTP
            return ResponseEntity.ok(response);


        } catch (AuthenticationException e) {
            
            return ResponseEntity.notFound().build();
        }
    }
}
