package com.example.smartapp.core.domain.response;

public class UserResponse {
    private int id;
    private String complete_name;
    private String phone;
    private String email;
    private int type;

    public UserResponse(int id, String complete_name, String phone, String email, int type) {
        this.id = id;
        this.complete_name = complete_name;
        this.phone = phone;
        this.email = email;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComplete_name() {
        return complete_name;
    }

    public void setComplete_name(String complete_name) {
        this.complete_name = complete_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
