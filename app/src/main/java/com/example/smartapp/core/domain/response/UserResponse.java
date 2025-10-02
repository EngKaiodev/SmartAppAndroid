package com.example.smartapp.core.domain.response;

import java.io.Serializable;

public class UserResponse implements Serializable {
    private int id;
    private String name;
    private int type;
    private String token;
    private String refreshToken;

    public UserResponse(int id, String name, int type, String token, String refreshToken) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
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
