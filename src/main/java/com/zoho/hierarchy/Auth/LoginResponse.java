package com.zoho.hierarchy.Auth;

public class LoginResponse {

    private String username;
    private String role;
    private String token;

    public LoginResponse(String username, String role, String token) {
        this.username = username;
        this.role = role;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public LoginResponse setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getRole() {
        return role;
    }

    public LoginResponse setRole(String role) {
        this.role = role;
        return this;
    }

    public String getToken() {
        return token;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }
}

