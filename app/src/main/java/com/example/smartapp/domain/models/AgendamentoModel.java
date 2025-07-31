package com.example.smartapp.domain.models;

import java.util.Date;

public class AgendamentoModel {
    private int id;
    private String name;
    private String servico;
    private String data;
    private String hora;
    private String valor;

    public AgendamentoModel(){

    }

    public AgendamentoModel(int id, String name, String servico, String data, String hora, String valor) {
        this.id = id;
        this.name = name;
        this.servico = servico;
        this.data = data;
        this.hora = hora;
        this.valor = valor;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getServico() {return servico;}

    public void setServico(String servico){this.servico = servico;}

    public String getData() {return data;}

    public void setData(String data) {this.data = data;}

    public String getHora() {return hora;}

    public void setHora(String hora) {this.hora = hora;}

    public String getValor() {return valor;}

    public void setValor(String valor) {this.valor = valor;}
}
