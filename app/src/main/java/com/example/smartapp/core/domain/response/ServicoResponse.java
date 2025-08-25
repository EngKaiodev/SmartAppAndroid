package com.example.smartapp.core.domain.response;

import java.io.Serializable;

public class ServicoResponse implements Serializable {

    private int id_servico;
    private ProfissionalResponse profissional;
    private String nome;
    private  String descricao;
    private  float preco;
    private int duracao;

    public ServicoResponse(int id_servico, ProfissionalResponse profissional, String nome, String descricao, float preco, int duracao) {
        this.id_servico = id_servico;
        this.profissional = profissional;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.duracao = duracao;
    }

    public int getId_servico() {
        return id_servico;
    }

    public void setId_servico(int id_servico) {
        this.id_servico = id_servico;
    }

    public ProfissionalResponse getProfissional() {
        return profissional;
    }

    public void setProfissional(ProfissionalResponse profissional) {
        this.profissional = profissional;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
