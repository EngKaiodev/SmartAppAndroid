package com.example.smartapp.core.domain.response;

import java.io.Serializable;
import java.util.List;

public class ClienteResponse implements Serializable {

    private int id_cliente;
    private String nome;
    private String email;
    private String telefone;
    private List<EnderecoResponse> enderecos;

    public ClienteResponse(int id_cliente, String nome, String email, String telefone, List<EnderecoResponse> enderecos) {
        this.id_cliente = id_cliente;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.enderecos = enderecos;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
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

    public List<EnderecoResponse> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoResponse> enderecos) {
        this.enderecos = enderecos;
    }
}
