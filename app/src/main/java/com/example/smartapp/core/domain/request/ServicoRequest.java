package com.example.smartapp.core.domain.request;

public class ServicoRequest {

    private int id_profissional;
    private String nome;
    private String descricacao;
    private float preco;
    private int duracao;

    public ServicoRequest(int id_profissional, String nome, String descricacao, float preco, int duracao) {
        this.id_profissional = id_profissional;
        this.nome = nome;
        this.descricacao = descricacao;
        this.preco = preco;
        this.duracao = duracao;
    }

    public int getId_profissional() {
        return id_profissional;
    }

    public void setId_profissional(int id_profissional) {
        this.id_profissional = id_profissional;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricacao() {
        return descricacao;
    }

    public void setDescricacao(String descricacao) {
        this.descricacao = descricacao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
}
