package com.example.smartapp.core.domain.response;

public class ServiceCompleteResponse {
    private int serviceId;
    private String serviceName;
    private double servicePrice;
    private int profissionalId;
    private String profissionalName;

    public ServiceCompleteResponse() {
    }

    public ServiceCompleteResponse(int serviceId, String serviceName, double servicePrice, int profissionalId, String profissionalName) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
        this.profissionalId = profissionalId;
        this.profissionalName = profissionalName;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

    public int getProfissionalId() {
        return profissionalId;
    }

    public void setProfissionalId(int profissionalId) {
        this.profissionalId = profissionalId;
    }

    public String getProfissionalName() {
        return profissionalName;
    }

    public void setProfissionalName(String profissionalName) {
        this.profissionalName = profissionalName;
    }
}
