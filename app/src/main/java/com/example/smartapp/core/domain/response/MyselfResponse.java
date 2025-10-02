package com.example.smartapp.core.domain.response;

public class MyselfResponse {
    private int id;
    private String completeName;
    private String phone;
    private String email;
    private String document;
    private int type;

    public MyselfResponse() {
    }

    public MyselfResponse(int id, String completeName, String phone, String email, String document, int type) {
        this.id = id;
        this.completeName = completeName;
        this.phone = phone;
        this.email = email;
        this.document = document;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompleteName() {
        return completeName;
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
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

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
