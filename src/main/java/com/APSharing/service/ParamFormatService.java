package com.APSharing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class ParamFormatService {

    private final LocalDate localDate;

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
