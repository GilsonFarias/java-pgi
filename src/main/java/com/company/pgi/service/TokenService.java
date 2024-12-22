package com.company.pgi.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.company.pgi.model.Users;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public String gerarToken(Users user) {
        return JWT.create()
            .withIssuer("users")
            .withSubject(user.getUsername())
            .withClaim("id", user.getId())
            .withExpiresAt(LocalDateTime.now()
                .plusMinutes(8)
                .toInstant(ZoneOffset.of("-04:00"))
            ).sign(Algorithm.HMAC256("secreta"));
    }

    public String getSubject(String token) {
        try {
            return JWT.require(Algorithm.HMAC256("secreta"))
                .withIssuer("users")
                .build().verify(token).getSubject();
            
        } catch (JWTVerificationException e) {
            throw new IllegalArgumentException("Token inválido ou expirado", e);
        }
    }

}
