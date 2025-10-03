package com.example.smartapp.core.domain.response;

import java.util.List;

public class ProfissionalDetailResponse {

    private ProfissionalResponse profissional;
    private List<ServicoResponse> servicos;
    private List<ReviewSimpleResponse> reviews;
    private AddressResponse address;

    public ProfissionalDetailResponse(ProfissionalResponse profissional, List<ServicoResponse> servicos, List<ReviewSimpleResponse> reviews, AddressResponse address) {
        this.profissional = profissional;
        this.servicos = servicos;
        this.reviews = reviews;
        this.address = address;
    }

    public AddressResponse getAddress() {
        return address;
    }

    public void setAddress(AddressResponse address) {
        this.address = address;
    }

    public ProfissionalResponse getProfissional() {
        return profissional;
    }

    public void setProfissional(ProfissionalResponse profissional) {
        this.profissional = profissional;
    }

    public List<ServicoResponse> getServicos() {
        return servicos;
    }

    public void setServicos(List<ServicoResponse> servicos) {
        this.servicos = servicos;
    }

    public List<ReviewSimpleResponse> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewSimpleResponse> reviews) {
        this.reviews = reviews;
    }
}
