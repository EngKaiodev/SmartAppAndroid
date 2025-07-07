package com.example.smartapp.core.domain.response;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class EnderecoResponse implements Serializable {
    private int id;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String pais;
    private String complemento;
    private String cep;

    public EnderecoResponse(int id, String rua, String numero, String bairro, String cidade, String pais, String complemento, String cep) {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.pais = pais;
        this.complemento = complemento;
        this.cep = cep;
    }

    public EnderecoResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @NonNull
    @Override
    public String toString() {
        return "Rua: " + rua +
                ", " + numero +
                " - " + bairro +
                " - " + cidade +
                "/" + pais +
                " - " + complemento +
                " - " + cep;
    }
}
