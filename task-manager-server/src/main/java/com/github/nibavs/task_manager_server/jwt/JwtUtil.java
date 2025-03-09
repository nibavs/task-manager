package com.github.nibavs.task_manager_server.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;


@Component
public class JwtUtil {
    // Make secret key static and from env variables
    private final SecretKey secretKey = Keys.hmacShaKeyFor("aklsdjflkdsajfjhsdafjhsdafj-jhsdfajkhadskjlfhdsakjhfkjlasdhf-dsklfjlksdjaflkjlkdsdsfaf".getBytes());

    public String generateToken(String username) {
        Date now = new Date();
        long expirationTime = 1800000; // 30 minutes
        Date expiryDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
