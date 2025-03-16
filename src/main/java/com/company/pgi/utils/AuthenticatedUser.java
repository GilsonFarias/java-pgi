package com.company.pgi.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.company.pgi.model.Users;

@Component
public class AuthenticatedUser {

    public static Users getAuthenticatedUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {

            // var userDetails = (UserDetails) authentication.getPrincipal();

            return (Users) authentication.getPrincipal();
        }

        return null;
    }

}
