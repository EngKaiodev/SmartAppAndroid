package com.example.smartapp.core.domain.response;

import java.io.Serializable;

public class ServicoResponse implements Serializable {

    private int id;
    private int proId;
    private String name;
    private  String description;
    private  float price;
    private int duration;

    public ServicoResponse(int id, int proId, String name, String description, float price, int duration) {
        this.id = id;
        this.proId = proId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
