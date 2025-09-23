package com.example.smartapp.core.domain.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProfissionalResponse implements Serializable {

    @SerializedName("id")
    private int id_profissional;
    @SerializedName("complete_name")
    private String name;
    @SerializedName("phone")
    private String contato;
    private String email;
    public ProfissionalResponse(int id_profissional, String name, String contato, String email) {
        this.id_profissional = id_profissional;
        this.name = name;
        this.contato = contato;
        this.email = email;
    }

    public int getId_profissional() {
        return id_profissional;
    }

    public void setId_profissional(int id_profissional) {
        this.id_profissional = id_profissional;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
