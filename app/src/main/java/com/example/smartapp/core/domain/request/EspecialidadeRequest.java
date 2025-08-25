package com.example.smartapp.core.domain.request;

import java.util.List;

public class EspecialidadeRequest {

    private int id;
    private String especialidade;
    private List<ProfissionalRequest> profissionais;

    public EspecialidadeRequest(int id, String especialidade, List<ProfissionalRequest> profissionais) {
        this.id = id;
        this.especialidade = especialidade;
        this.profissionais = profissionais;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public List<ProfissionalRequest> getProfissionais() {
        return profissionais;
    }

    public void setProfissionais(List<ProfissionalRequest> profissionais) {
        this.profissionais = profissionais;
    }
}
