package com.example.smartapp.core.domain.response;

import java.io.Serializable;

public class ScheduleResponse implements Serializable {
    private int id;
    private ProfissionalResponse profissional;
    private ServicoResponse servico;
    private HourResponse hour;

    public ScheduleResponse() {
    }

    public ScheduleResponse(int id, ProfissionalResponse profissional, ServicoResponse servico, HourResponse hour) {
        this.id = id;
        this.profissional = profissional;
        this.servico = servico;
        this.hour = hour;
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
}
