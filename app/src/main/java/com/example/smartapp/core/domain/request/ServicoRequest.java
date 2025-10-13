package com.example.smartapp.core.domain.request;



public class ServicoRequest {
    private String name;
    private String description;
    private double price;
    private int duration;
    private int proId;

    @Override
    public String toString() {
        return "ServicoRequest{" +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                ", proId=" + proId +
                '}';
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

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public ServicoRequest() {
    }

    public ServicoRequest(String name, String description, double price, int duration, int proId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.proId = proId;
    }
}
