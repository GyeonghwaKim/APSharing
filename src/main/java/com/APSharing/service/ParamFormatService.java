package com.APSharing.service;

import com.APSharing.LocalDateParam;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Service
public class ParamFormatService {

    private LocalDate localDate=LocalDate.now();

    public String getSolYear(){
        return localDate.format(DateTimeFormatter.ofPattern("yyyy"));
    }

    public String getSolMonth(){
        return localDate.format(DateTimeFormatter.ofPattern("MM"));
    }

    public String getSolDay(){
        return localDate.format(DateTimeFormatter.ofPattern("dd"));
    }


}
