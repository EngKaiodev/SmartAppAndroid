package com.example.smartapp.core.domain.response;

public class UserResponse {
    private int id;
    private int type;
    private String token;

    public UserResponse(int id, int type, String token) {
        this.id = id;
        this.type = type;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
