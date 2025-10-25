package com.example.smartapp.core.domain.response;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Locale;


public class ServicoResponse implements Serializable {
    private int id;
    private String name;
    private String description;
    private double price;
    private int duration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ServicoResponse() {
    }

    public ServicoResponse(int id, String name, String description, double price, int duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
    }

    @NonNull
    @Override
    public String toString() {
        return name + " - R$ " + String.format(Locale.getDefault(), "%.2f", price) ;
    }

    public String returnPrice(){
        return "R$ " + String.format(Locale.getDefault(), "%.2f", price);
    }
}
