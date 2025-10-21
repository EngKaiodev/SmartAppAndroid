package com.example.smartapp.core.domain.response;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class HourResponse {
    private int id;
    private LocalDateTime initHour;
    private LocalDateTime endHour;
    private String status;

    public HourResponse() {
    }

    public HourResponse(int id, LocalDateTime initHour, LocalDateTime endHour, String status) {
        this.id = id;
        this.initHour = initHour;
        this.endHour = endHour;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getInitHour() {
        return initHour;
    }

    public void setInitHour(LocalDateTime initHour) {
        this.initHour = initHour;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public String toString() {
        return initHour.toLocalTime().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"));
    }

}
