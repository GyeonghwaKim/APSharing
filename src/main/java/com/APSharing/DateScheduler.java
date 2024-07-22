package com.APSharing;


import com.APSharing.service.DateService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DateScheduler {



    private final DateService dateService;

    @PostConstruct
    private void init(){
        this.dateService.updateToday();
        log.info("Init Today = {}",this.dateService.getToday());
    }

    @Scheduled(cron = "0 0 0 * * ?")
    private void scheduledUpdateToday(){
        this.dateService.updateToday();
        log.info("ScheduledUpdate Today = {}",this.dateService.getToday());
    }

}
