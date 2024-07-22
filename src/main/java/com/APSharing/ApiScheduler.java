package com.APSharing;


import com.APSharing.service.api.ApiService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiScheduler {

    private final @Qualifier("kasiApiService") ApiService kasiApiService;

    private final @Qualifier("nasaApiService") ApiService nasaApiService;

    @PostConstruct
    private void init(){
        this.kasiApiService.refreshApiResponse();
        this.nasaApiService.refreshApiResponse();
        log.info("init kasiApiResponse = {}",this.kasiApiService.getApiResponse());
        log.info("init nasaApiResponse = {}",this.nasaApiService.getApiResponse());
    }

    @Scheduled(cron="0 0 0 * * ?")
    public void refreshKasiApiRespMap(){
        this.kasiApiService.refreshApiResponse();
        log.info("kasiApiResponse = {}",this.kasiApiService.getApiResponse());
    }

    @Scheduled(cron="0 0 13 * * ?")
    public void refreshNasaApiResponse(){
        this.nasaApiService.refreshApiResponse();
        log.info("nasaApiResponse = {}",this.nasaApiService.getApiResponse());
    }


}
