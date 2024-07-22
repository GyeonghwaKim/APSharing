package com.APSharing.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class DateService {

    private LocalDate today;

    @PostConstruct
    private void init(){
        updateToday();
    }

    public LocalDate getToday() {
        return today;
    }

    public void updateToday(){
        today=LocalDate.now();
    }

    public String getSolYear(){
        String year= today.format(DateTimeFormatter.ofPattern("yyyy"));

        return year;
    }

    public String getSolMonth(){
        String month= today.format(DateTimeFormatter.ofPattern("MM"));
        return month;
    }

    public String getSolDay(){
        String day= today.format(DateTimeFormatter.ofPattern("dd"));
        return day;
    }


}
