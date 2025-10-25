package com.example.smartapp.core.domain.request;

public class ScheduleRequest {
    private int userId;
    private int proId;
    private int serviceId;
    private int hourId;

    public ScheduleRequest() {
    }

    public ScheduleRequest(int userId, int proId, int serviceId, int hourId) {
        this.userId = userId;
        this.proId = proId;
        this.serviceId = serviceId;
        this.hourId = hourId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getHourId() {
        return hourId;
    }

    public void setHourId(int hourId) {
        this.hourId = hourId;
    }
}
