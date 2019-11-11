package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * DTO presents {"username":"adminUser", "password":"xxx"} 
 * 
 * */

public class LoginDataDTO {

    @JsonProperty("username")
    private String userName;
    private String password;

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

}
