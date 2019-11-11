package com.example.demo.dto;

/*
 * DTO presents {"message":"unauthorized"} 
 * 
 * */

public class LoginFailDTO {

    private String message;

    public LoginFailDTO(String message) {
	super();
	this.message = message;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

}
