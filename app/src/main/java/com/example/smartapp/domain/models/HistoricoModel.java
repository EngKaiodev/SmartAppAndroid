package com.example.smartapp.domain.models;

public class HistoricoModel {
    private int id;
    private String name;
    private String servico;
    private String data;
    private String valor;
    private String avaliacao;

    public HistoricoModel(int id, String name, String servico, String data, String valor, String avaliacao) {
        this.id = id;
        this.name = name;
        this.servico = servico;
        this.data = data;
        this.valor = valor;
        this.avaliacao = avaliacao;
    }

    public HistoricoModel(){

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }
}
