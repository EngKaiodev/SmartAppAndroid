package com.example.smartapp.core.domain.request;

public class LoginRequest {
    private String email;
    private String password;
    private String tokenFCM;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTokenFCM() {
        return tokenFCM;
    }

    public void setTokenFCM(String tokenFCM) {
        this.tokenFCM = tokenFCM;
    }

    public LoginRequest(String email, String password, String tokenFCM) {
        this.email = email;
        this.password = password;
        this.tokenFCM = tokenFCM;
    }
}
