package com.example.smartapp.core.domain.request;

public class ClienteRequest {
    private int clienteID;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private String cpf_cnpj;

    public ClienteRequest(int clienteID, String nome, String email, String telefone, String senha, String cpf_cnpj) {
        this.clienteID = clienteID;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.cpf_cnpj = cpf_cnpj;
    }

    public int getClienteID() {
        return clienteID;
    }

    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }
}
