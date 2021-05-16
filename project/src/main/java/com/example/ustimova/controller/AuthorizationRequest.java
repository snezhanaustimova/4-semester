package com.example.ustimova.controller;

import lombok.Data;

@Data
public class AuthorizationRequest {
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
