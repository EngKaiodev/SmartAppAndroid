package com.example.smartapp.core.domain.response;

public class RefreshResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RefreshResponse(String token) {
        this.token = token;
    }
}
