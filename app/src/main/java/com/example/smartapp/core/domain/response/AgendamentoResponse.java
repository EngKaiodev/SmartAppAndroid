package com.example.smartapp.core.domain.response;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AgendamentoResponse implements Serializable {

    private  int id;
    private ClienteResponse cliente;
    private ServicoResponse servico;
    private HorarioDisponivelResponse horario;
    private String Status;
    private int nota;
    private String comentario;
    private LocalDateTime data_hora_criacao;

    public AgendamentoResponse(int id, ClienteResponse cliente, ServicoResponse servico, HorarioDisponivelResponse horario, String status, int nota, String comentario, LocalDateTime data_hora_criacao) {
        this.id = id;
        this.cliente = cliente;
        this.servico = servico;
        this.horario = horario;
        Status = status;
        this.nota = nota;
        this.comentario = comentario;
        this.data_hora_criacao = data_hora_criacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClienteResponse getCliente() {
        return cliente;
    }

    public void setCliente(ClienteResponse cliente) {
        this.cliente = cliente;
    }

    public ServicoResponse getServico() {
        return servico;
    }

    public void setServico(ServicoResponse servico) {
        this.servico = servico;
    }

    public HorarioDisponivelResponse getHorario() {
        return horario;
    }

    public void setHorario(HorarioDisponivelResponse horario) {
        this.horario = horario;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getData_hora_criacao() {
        return data_hora_criacao;
    }

    public void setData_hora_criacao(LocalDateTime data_hora_criacao) {
        this.data_hora_criacao = data_hora_criacao;
    }
}
