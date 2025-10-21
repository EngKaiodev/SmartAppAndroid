package com.example.smartapp.core.domain.request;

import java.time.LocalDateTime;


public class HoursRequest {
    private Integer userId;
    private LocalDateTime initHour;
    private LocalDateTime endHour;
    private String status;

    public HoursRequest() {
    }

    public HoursRequest(Integer userId, LocalDateTime initHour, LocalDateTime endHour, String status) {
        this.userId = userId;
        this.initHour = initHour;
        this.endHour = endHour;
        this.status = status;
    }

    public LocalDateTime getInitHour() {
        return initHour;
    }

    public void setInitHour(LocalDateTime initHour) {
        this.initHour = initHour;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getEndHour() {
        return endHour;
    }

    public void setEndHour(LocalDateTime endHour) {
        this.endHour = endHour;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "HoursRequest{" +
                "userId=" + userId +
                ", initHour=" + initHour +
                ", endHour=" + endHour +
                ", status='" + status + '\'' +
                '}';
    }
}
