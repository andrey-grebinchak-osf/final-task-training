package com.example.demo.security.filter;

import com.example.demo.dto.LoginFailDTO;
import com.example.demo.service.JwtTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthenticationFilter extends GenericFilterBean {

    private JwtTokenService tokenService;

    public AuthenticationFilter(JwtTokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String token = ((HttpServletRequest) req).getHeader(LoginFilter.TOKEN_HEADER);
        if (token != null) {
            Authentication authentication = tokenService.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(req, res);
        } else {
            LoginFailDTO loginFailDTO = new LoginFailDTO("unauthorized");
            ObjectMapper objectMapper = new ObjectMapper();
            byte[] objectBytes = objectMapper.writeValueAsBytes(loginFailDTO);
            res.getOutputStream().write(objectBytes);
        }
    }
}
