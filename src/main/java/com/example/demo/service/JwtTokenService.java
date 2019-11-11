package com.example.demo.service;

import com.example.demo.dto.AuthSuccessDTO;
import org.springframework.security.core.Authentication;

public interface JwtTokenService {

    AuthSuccessDTO generateToken(String userName);

    Authentication getAuthentication(String token);


}
