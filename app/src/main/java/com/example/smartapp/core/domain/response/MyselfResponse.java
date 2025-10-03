package com.example.smartapp.core.domain.response;

public class MyselfResponse {
    private int id;
    private String completeName;
    private String phone;
    private String email;
    private String document;
    private int type;
    private AddressResponse address;
    private String alterName;
    private String description;

    public MyselfResponse() {
    }

    public MyselfResponse(int id, String completeName, String phone, String email, String document, int type, AddressResponse address, String alterName, String description) {
        this.id = id;
        this.completeName = completeName;
        this.phone = phone;
        this.email = email;
        this.document = document;
        this.type = type;
        this.address = address;
        this.alterName = alterName;
        this.description = description;
    }

    public AddressResponse getAddress() {
        return address;
    }

    public void setAddress(AddressResponse address) {
        this.address = address;
    }

    public MyselfResponse(int id, String completeName, String phone, String email, String document, int type, AddressResponse address) {
        this.id = id;
        this.completeName = completeName;
        this.phone = phone;
        this.email = email;
        this.document = document;
        this.type = type;
        this.address = address;
    }

    public String getAlterName() {
        return alterName;
    }

    public void setAlterName(String alterName) {
        this.alterName = alterName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
