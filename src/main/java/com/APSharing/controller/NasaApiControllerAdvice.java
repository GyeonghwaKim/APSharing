package com.APSharing.controller;

import com.APSharing.service.JsonService;
import com.APSharing.vo.nasa.Apod;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class NasaApiControllerAdvice {

    private final JsonService jsonService;

    @Value("${NasaKey}")
    private String nasaKey;

    @Value("${ApodUrl}")
    private String nasaApodUrl;

    @ModelAttribute
    public void callApod(Model model){


        String urlStr = nasaApodUrl +
                "api_key=" + nasaKey;

        String result = this.jsonService.getJson(urlStr);

        Apod response=this.jsonService.parseJson(result, Apod.class);
        model.addAttribute("apod",response);
    }
}
