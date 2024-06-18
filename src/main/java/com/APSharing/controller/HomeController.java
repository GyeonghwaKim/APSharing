package com.APSharing.controller;


import com.APSharing.Scheduler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final Scheduler date;

    @GetMapping("/")
    public String home(Model model)
    {

        LocalDate today = date.getToday();

        model.addAttribute("today",today);
        return "main";
    }
}
