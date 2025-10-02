package com.example.smartapp.core.domain.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProfissionalResponse implements Serializable {

    @SerializedName("id")
    private int id_profissional;
    @SerializedName("completeName")
    private String name;
    @SerializedName("phone")
    private String contato;
    private Double rate;

    private int reviewsCount;

    public ProfissionalResponse(int id_profissional, String name, String contato, Double rate, int reviewsCount) {
        this.id_profissional = id_profissional;
        this.name = name;
        this.contato = contato;
        this.rate = rate;
        this.reviewsCount = reviewsCount;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public int getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(int reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public int getId_profissional() {
        return id_profissional;
    }

    public void setId_profissional(int id_profissional) {
        this.id_profissional = id_profissional;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}
