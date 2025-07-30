package com.example.smartapp.domain.models;

public class ProfissionalModel {

    private  int id;

    private String name;

    private String especialidade;

    private String avaliacao;

    public ProfissionalModel(){

    }

    public ProfissionalModel(int id, String name, String especialidade, String avaliacao) {
        this.id = id;
        this.name = name;
        this.especialidade = especialidade;
        this.avaliacao = avaliacao;
    }

    public int getId() {return id;}

    public String getEspecialidade() {return especialidade;}

    public void setEspecialidade(String especialidade) {this.especialidade = especialidade;}

    public String getAvaliacao() {return avaliacao;}

    public void setAvaliacao(String avaliacao) {this.avaliacao = avaliacao;}

    public void setId(int id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}
}
