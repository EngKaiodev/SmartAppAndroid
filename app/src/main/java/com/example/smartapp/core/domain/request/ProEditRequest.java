package com.example.smartapp.core.domain.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProEditRequest {
    private String completeName;
    private String phone;
    private String email;
    private String document;
    private String alterName;
    private String description;
    private String zipcode;
    private String address;
    private String number;

    public ProEditRequest(String completeName, String phone, String email, String document, String alterName, String description, String zipcode, String address, String number) {
        this.completeName = completeName;
        this.phone = phone;
        this.email = email;
        this.document = document;
        this.alterName = alterName;
        this.description = description;
        this.zipcode = zipcode;
        this.address = address;
        this.number = number;
    }
}
