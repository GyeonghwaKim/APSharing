package com.APSharing.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final LocalDate localDate;

    @GetMapping("/")
    public String home(Model model)
    {

        model.addAttribute("today",localDate);
        return "main";
    }
}
