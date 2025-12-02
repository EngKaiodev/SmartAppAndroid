package com.example.smartapp.core.domain.response;

public class ClienteSimpleResponse {
    private int id;
    private String completeName;

    public ClienteSimpleResponse() {
    }

    public ClienteSimpleResponse(int id, String completeName) {
        this.id = id;
        this.completeName = completeName;
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
}
