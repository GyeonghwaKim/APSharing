package com.APSharing;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {

    private LocalDate today;

    @PostConstruct
    private void init(){
        updateToday();
        log.info("Init Today = {}",today);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    private void scheduledUpdateToday(){
        updateToday();
        log.info("ScheduledUpdate Today = {}", today);

    }




    private void updateToday() {
        today = LocalDate.now();
    }

    public LocalDate getToday() {
        return today;
    }


}
