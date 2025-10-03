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
    private String altername;
    private String description;

    public ProfissionalResponse(int id_profissional, String name, String contato, Double rate, int reviewsCount, String altername, String description) {
        this.id_profissional = id_profissional;
        this.name = name;
        this.contato = contato;
        this.rate = rate;
        this.reviewsCount = reviewsCount;
        this.altername = altername;
        this.description = description;
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

    public String getAltername() {
        return altername;
    }

    public void setAltername(String altername) {
        this.altername = altername;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}