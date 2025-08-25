package com.example.smartapp.core.domain.response;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class HorarioDisponivelResponse implements Serializable {

    private int id;
    private LocalDate data;
    private LocalTime hora_inicio;
    private LocalTime hora_fim;
    private String status;
    private ProfissionalResponse profissional;

    public HorarioDisponivelResponse(int id, LocalDate data, LocalTime hora_inicio, LocalTime hora_fim, String status, ProfissionalResponse profissional) {
        this.id = id;
        this.data = data;
        this.hora_inicio = hora_inicio;
        this.hora_fim = hora_fim;
        this.status = status;
        this.profissional = profissional;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(LocalTime hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public LocalTime getHora_fim() {
        return hora_fim;
    }

    public void setHora_fim(LocalTime hora_fim) {
        this.hora_fim = hora_fim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProfissionalResponse getProfissional() {
        return profissional;
    }

    public void setProfissional(ProfissionalResponse profissional) {
        this.profissional = profissional;
    }
}
