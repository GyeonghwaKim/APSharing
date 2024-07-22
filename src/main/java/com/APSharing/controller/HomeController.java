package com.APSharing.controller;


import com.APSharing.DateScheduler;
import com.APSharing.service.DateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final DateService dateService;

    @GetMapping("/")
    public String home(Model model)
    {

        LocalDate today = dateService.getToday();

        model.addAttribute("today",today);
        return "main";
    }

    @Value("${server.env}")
    private String env;
    @Value("${server.port}")
    private String serverPort;
    @Value("${server.serverAddress}")
    private String serverAddress;
    @Value("${serverName}")
    private String serverName;

    @GetMapping("/serverCheck")
    public ResponseEntity<?> serverCheck(){
        Map<String,String> responseData=new TreeMap<>();

        responseData.put("serverName",serverName);
        responseData.put("serverPort",serverPort);
        responseData.put("serverAddress",serverAddress);
        responseData.put("env",env);

        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/env")
    public ResponseEntity<?> getEnv(){

        return ResponseEntity.ok(env);
    }


}
