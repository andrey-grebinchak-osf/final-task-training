package com.example.demo.service;

import com.example.demo.dto.AuthSuccessDTO;
import com.example.demo.repo.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

@Service
public class JwtTokenService {

    private final Long EXPIRATION_TIME = 1_800_000L; // 30min
    private final String SECRET_KEY = "secretKey";

    @Autowired
    private UserRepository userRepository;

    /*
     * method for token generation
     * */
    public AuthSuccessDTO generateToken(String userName) {
        Date expirationTime = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        String token = Jwts.builder().setSubject(userName).setExpiration(expirationTime)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
        return new AuthSuccessDTO(token, expirationTime, "success");

    }

    public Authentication getAuthentication(String token) {
        try {
            String user = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();

            return user != null ? new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) : null;
        } catch (ExpiredJwtException ex) {
            return null;
        }
    }

}
