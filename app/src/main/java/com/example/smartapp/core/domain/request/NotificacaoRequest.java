package com.example.smartapp.core.domain.request;

import java.util.Date;

public class NotificacaoRequest {

    private int id_notificacao;
    private int id_agendamento;
    private String mensagem;
    private Date data_envio;

    public NotificacaoRequest(int id_notificacao, int id_agendamento, String mensagem, Date data_envio) {
        this.id_notificacao = id_notificacao;
        this.id_agendamento = id_agendamento;
        this.mensagem = mensagem;
        this.data_envio = data_envio;
    }

    public int getId_notificacao() {
        return id_notificacao;
    }

    public void setId_notificacao(int id_notificacao) {
        this.id_notificacao = id_notificacao;
    }

    public int getId_agendamento() {
        return id_agendamento;
    }

    public void setId_agendamento(int id_agendamento) {
        this.id_agendamento = id_agendamento;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Date getData_envio() {
        return data_envio;
    }

    public void setData_envio(Date data_envio) {
        this.data_envio = data_envio;
    }
}
