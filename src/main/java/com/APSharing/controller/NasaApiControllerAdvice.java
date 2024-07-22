package com.APSharing.controller;

import com.APSharing.service.JsonService;
import com.APSharing.service.api.ApiService;
import com.APSharing.vo.nasa.Apod;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class NasaApiControllerAdvice {

    private final JsonService jsonService;
    private final @Qualifier("nasaApiService") ApiService nasaApiService;


    @ModelAttribute
    public void callApod(Model model){

        String result= nasaApiService.getApiResponse().get("apod");
        Apod response=this.jsonService.parseJson(result, Apod.class);
        model.addAttribute("apod",response);
    }
}
