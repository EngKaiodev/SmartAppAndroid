package com.example.smartapp.core.domain.request;

public class ProfissionalRequest {

    private int id_profissional;
    private String razao_social;
    private String contato;
    private String email;
    private String senha;
    private String cnpj;

    public ProfissionalRequest(int id_profissional, String razao_social, String contato, String email, String senha, String cnpj) {
        this.id_profissional = id_profissional;
        this.razao_social = razao_social;
        this.contato = contato;
        this.email = email;
        this.senha = senha;
        this.cnpj = cnpj;
    }

    public int getId_profissional() {
        return id_profissional;
    }

    public void setId_profissional(int id_profissional) {
        this.id_profissional = id_profissional;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
