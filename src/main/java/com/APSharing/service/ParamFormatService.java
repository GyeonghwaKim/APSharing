package com.APSharing.service;

import com.APSharing.Scheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class ParamFormatService {


    private final Scheduler dateUpdater;


    public String getSolYear(){
        String year= dateUpdater.getToday().format(DateTimeFormatter.ofPattern("yyyy"));

        return year;
    }

    public String getSolMonth(){
        String month= dateUpdater.getToday().format(DateTimeFormatter.ofPattern("MM"));
        return month;
    }

    public String getSolDay(){
        String day= dateUpdater.getToday().format(DateTimeFormatter.ofPattern("dd"));
        return day;
    }


}
