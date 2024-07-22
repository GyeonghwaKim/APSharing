package com.APSharing.service.api;

import com.APSharing.service.JsonService;
import com.APSharing.service.api.ApiService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class NasaApiService implements ApiService {

    @Value("${NasaKey}")
    private String nasaKey;

    @Value("${ApodUrl}")
    private String nasaApodUrl;

    private Map<String,String> apiResponse;

    private final JsonService jsonService;


    @PostConstruct
    private void init(){
        apiResponse=new HashMap<>();
    }

    @Override
    public Map<String, String> getApiResponse() {
        return apiResponse;
    }

    @Override
    public void refreshApiResponse() {
        fetchApiResponse();
    }

    private void fetchApiResponse() {
        fetchApod();
    }

    private void fetchApod() {
        String urlStr = nasaApodUrl + "api_key=" + nasaKey;
        String result = this.jsonService.getJson(urlStr);

        apiResponse.put("apod",result);
    }

}
