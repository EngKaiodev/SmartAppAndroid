package com.example.smartapp.domain.models;

public class AvaliacaoModel {

    private int id;
    private String nome;
    private String avaliar;
    private String comentario;
    private String data;


    public AvaliacaoModel(int id, String nome, String avaliar, String comentario, String data) {
        this.id = id;
        this.nome = nome;
        this.avaliar = avaliar;
        this.comentario = comentario;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAvaliar() {
        return avaliar;
    }

    public void setAvaliar(String avaliar) {
        this.avaliar = avaliar;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
