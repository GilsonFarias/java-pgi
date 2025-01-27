package com.company.pgi.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.company.pgi.model.Users;

@Service
public class TokenService {

    public String gerarToken(Users user) {
        return JWT.create()
            .withIssuer("users")
            .withSubject(user.getUsername())
            .withClaim("id", user.getId())
            .withExpiresAt(LocalDateTime.now()
                .plusHours(8)
                .toInstant(ZoneOffset.of("-04:00"))
            ).sign(Algorithm.HMAC256("secreta_54KHeG4D5q5GtoIO6Tv6Ya2W6çWEqR6YyR"));
    }

    public String getSubject(String token) {
        try {
            return JWT.require(Algorithm.HMAC256("secreta_54KHeG4D5q5GtoIO6Tv6Ya2W6çWEqR6YyR"))
                .withIssuer("users")
                .build().verify(token).getSubject();
            
        } catch (JWTVerificationException e) {
            throw new IllegalArgumentException("Token inválido ou expirado", e);
        }
    }

}
