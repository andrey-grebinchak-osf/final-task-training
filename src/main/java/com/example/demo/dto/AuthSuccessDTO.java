package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/*
 * DTO presents {"message": "success", "token":"xxx", "expiresAt":"2018-01-01 00:00:00"}
 *  
 * */

public class AuthSuccessDTO {

    private String token;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expiresAt;

    private String message;

    public AuthSuccessDTO(String token, Date expiresAt, String message) {
        super();
        this.token = token;
        this.expiresAt = expiresAt;
        this.message = message;
    }

    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }

    public Date getExpiresAt() {
	return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
	this.expiresAt = expiresAt;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

}
