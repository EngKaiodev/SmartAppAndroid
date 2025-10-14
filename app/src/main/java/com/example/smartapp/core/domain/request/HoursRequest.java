package com.example.smartapp.core.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class HoursRequest {
    private Integer userId;
    private Integer day; // 1 = segunda ... 7 = domingo
    private int initHourMorning;
    private int initMinuteMorning;
    private int endHourMorning;
    private int endMinuteMorning;
    private int initHourAfternoon;
    private int initMinuteAfternoon;
    private int endHourAfternoon;
    private int endMinuteAfternoon;

    public HoursRequest() {
    }

    public HoursRequest(Integer userId, Integer day, int initHourMorning, int initMinuteMorning, int endHourMorning, int endMinuteMorning, int initHourAfternoon, int initMinuteAfternoon, int endHourAfternoon, int endMinuteAfternoon) {
        this.userId = userId;
        this.day = day;
        this.initHourMorning = initHourMorning;
        this.initMinuteMorning = initMinuteMorning;
        this.endHourMorning = endHourMorning;
        this.endMinuteMorning = endMinuteMorning;
        this.initHourAfternoon = initHourAfternoon;
        this.initMinuteAfternoon = initMinuteAfternoon;
        this.endHourAfternoon = endHourAfternoon;
        this.endMinuteAfternoon = endMinuteAfternoon;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public int getInitHourMorning() {
        return initHourMorning;
    }

    public void setInitHourMorning(int initHourMorning) {
        this.initHourMorning = initHourMorning;
    }

    public int getInitMinuteMorning() {
        return initMinuteMorning;
    }

    public void setInitMinuteMorning(int initMinuteMorning) {
        this.initMinuteMorning = initMinuteMorning;
    }

    public int getEndHourMorning() {
        return endHourMorning;
    }

    public void setEndHourMorning(int endHourMorning) {
        this.endHourMorning = endHourMorning;
    }

    public int getEndMinuteMorning() {
        return endMinuteMorning;
    }

    public void setEndMinuteMorning(int endMinuteMorning) {
        this.endMinuteMorning = endMinuteMorning;
    }

    public int getInitHourAfternoon() {
        return initHourAfternoon;
    }

    public void setInitHourAfternoon(int initHourAfternoon) {
        this.initHourAfternoon = initHourAfternoon;
    }

    public int getInitMinuteAfternoon() {
        return initMinuteAfternoon;
    }

    public void setInitMinuteAfternoon(int initMinuteAfternoon) {
        this.initMinuteAfternoon = initMinuteAfternoon;
    }

    public int getEndHourAfternoon() {
        return endHourAfternoon;
    }

    public void setEndHourAfternoon(int endHourAfternoon) {
        this.endHourAfternoon = endHourAfternoon;
    }

    public int getEndMinuteAfternoon() {
        return endMinuteAfternoon;
    }

    public void setEndMinuteAfternoon(int endMinuteAfternoon) {
        this.endMinuteAfternoon = endMinuteAfternoon;
    }
}
