package com.example.smartapp.core.domain.request;

import java.time.LocalDate;
import java.time.LocalTime;

public class HorarioDisponivelRequest {

    private int id_profissional;
    private LocalDate data;
    private LocalTime hora_inicio;
    private LocalTime hora_fim;
    private String status;

    public HorarioDisponivelRequest(int id_profissional, LocalDate data, LocalTime hora_inicio, LocalTime hora_fim, String status) {
        this.id_profissional = id_profissional;
        this.data = data;
        this.hora_inicio = hora_inicio;
        this.hora_fim = hora_fim;
        this.status = status;
    }

    public int getId_profissional() {
        return id_profissional;
    }

    public void setId_profissional(int id_profissional) {
        this.id_profissional = id_profissional;
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
}
