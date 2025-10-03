package com.example.smartapp.core.domain.request;

public class UserRequest {

    private String complete_name;
    private String password;
    private String phone;
    private String email;
    private String document;
    private Integer type;

    private String alterName;
    private String description;

    private String zipcode;
    private String address;
    private String number;



    public UserRequest() {
    }

    public UserRequest(String complete_name, String password, String phone, String email, String document, Integer type, String alterName, String description, String zipcode, String address, String number) {
        this.complete_name = complete_name;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.document = document;
        this.type = type;
        this.alterName = alterName;
        this.description = description;
        this.zipcode = zipcode;
        this.address = address;
        this.number = number;
    }

    public UserRequest(String complete_name, String password, String phone, String email, String document, Integer type, String zipcode, String address, String number) {
        this.complete_name = complete_name;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.document = document;
        this.type = type;
        this.zipcode = zipcode;
        this.address = address;
        this.number = number;
    }

    public String getComplete_name() {
        return complete_name;
    }

    public void setComplete_name(String complete_name) {
        this.complete_name = complete_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
