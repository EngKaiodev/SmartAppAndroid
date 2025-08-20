package com.example.smartapp.core.domain.request;

public class AgendamentoRequest {
    private int clienteId;
    private int servicoId;
    private int horarioId;
    private String status;
    private int nota;
    private String comentarios;
    private String dataHoraCriacao;

    public AgendamentoRequest() {
    }

    public AgendamentoRequest(int clienteId, int servicoId, int horarioId, String status, int nota, String comentarios, String dataHoraCriacao) {
        this.clienteId = clienteId;
        this.servicoId = servicoId;
        this.horarioId = horarioId;
        this.status = status;
        this.nota = nota;
        this.comentarios = comentarios;
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getServicoId() {
        return servicoId;
    }

    public void setServicoId(int servicoId) {
        this.servicoId = servicoId;
    }

    public int getHorarioId() {
        return horarioId;
    }

    public void setHorarioId(int horarioId) {
        this.horarioId = horarioId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(String dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }
}
