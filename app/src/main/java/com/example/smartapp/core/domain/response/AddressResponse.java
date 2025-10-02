package com.example.smartapp.core.domain.response;

public class AddressResponse {
    private int id;
    private String zipCode;
    private String address;
    private String number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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

    public AddressResponse(int id, String zipCode, String address, String number) {
        this.id = id;
        this.zipCode = zipCode;
        this.address = address;
        this.number = number;
    }

    public AddressResponse() {
    }
}
