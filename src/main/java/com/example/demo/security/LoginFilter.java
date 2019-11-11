package com.example.demo.security;

import com.example.demo.dto.AuthSuccessDTO;
import com.example.demo.dto.LoginDataDTO;
import com.example.demo.dto.LoginFailDTO;
import com.example.demo.service.JwtTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    // header name
    protected static final String TOKEN_HEADER = "Authorization";

    // url for login
    private static final String LOGIN_URL = "/finaltask/login";

    private JwtTokenService tokenService;

    public LoginFilter(AuthenticationManager authManager, JwtTokenService tokenService) {
        super(LOGIN_URL);
        setAuthenticationManager(authManager);
        this.tokenService = tokenService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException {
        BufferedReader reader = req.getReader();
        ObjectMapper objectMapper = new ObjectMapper();

        // load login information and populate DTO fields with data with
        // generated token info
        LoginDataDTO loginData = objectMapper.readValue(reader, LoginDataDTO.class);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginData.getUserName(),
                loginData.getPassword(), Collections.emptyList());
        return getAuthenticationManager().authenticate(token);
    }

    /*
     * when login is successful token is generate and AuthSuccessDTO results
     * {"message": "success", "token":"xxx", "expiresAt":"2018-01-01 00:00:00"}
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException {
        AuthSuccessDTO loginSuccess = tokenService.generateToken(authResult.getName());
        response.setHeader(TOKEN_HEADER, loginSuccess.getToken());
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] objectBytes = objectMapper.writeValueAsBytes(loginSuccess);
        response.getOutputStream().write(objectBytes);
    }

    /*
     * when login is unsuccessful LoginFailDTO results
     * {"message":"unauthorized"}
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        LoginFailDTO loginFailDTO = new LoginFailDTO("unauthorized");
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] objectBytes = objectMapper.writeValueAsBytes(loginFailDTO);
        System.out.println(objectBytes);
        response.getOutputStream().write(objectBytes);
    }
}
