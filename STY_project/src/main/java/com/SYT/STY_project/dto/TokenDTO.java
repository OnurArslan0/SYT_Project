package com.SYT.STY_project.dto;


public class TokenDTO {
    private String token;
    private String userName;

    public TokenDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
