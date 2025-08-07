package com.example.smartapp.domain.models;

public class ServicoModel {
    private int id;
    private String name;
    private String servico;
    private String valor;

    public ServicoModel(){

    }

    public ServicoModel(int id, String name, String servico, String valor) {
        this.id = id;
        this.name = name;
        this.servico = servico;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
