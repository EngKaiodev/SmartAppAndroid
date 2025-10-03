package com.example.smartapp.core.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class HoursRequest {
    private int day; // 1 = segunda ... 7 = domingo
    private int initHourMorning;
    private int endHourMorning;
    private int initHourAfternoon;
    private int endHourAfternoon;
}
