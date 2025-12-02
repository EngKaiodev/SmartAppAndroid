package com.example.smartapp.core.domain.response;

public class HistoricResponse {
    private int id;
    private ProfissionalResponse profissional;
    private ServicoResponse servico;
    private HourResponse hour;
    private int status;
    private int rate;
    private ClienteSimpleResponse cliente;

    public HistoricResponse() {
    }

    public HistoricResponse(int id, ProfissionalResponse profissional, ServicoResponse servico, HourResponse hour, int status, int rate, ClienteSimpleResponse cliente) {
        this.id = id;
        this.profissional = profissional;
        this.servico = servico;
        this.hour = hour;
        this.status = status;
        this.rate = rate;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProfissionalResponse getProfissional() {
        return profissional;
    }

    public void setProfissional(ProfissionalResponse profissional) {
        this.profissional = profissional;
    }

    public ClienteSimpleResponse getCliente() {
        return cliente;
    }

    public void setCliente(ClienteSimpleResponse cliente) {
        this.cliente = cliente;
    }

    public ServicoResponse getServico() {
        return servico;
    }

    public void setServico(ServicoResponse servico) {
        this.servico = servico;
    }

    public HourResponse getHour() {
        return hour;
    }

    public void setHour(HourResponse hour) {
        this.hour = hour;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
