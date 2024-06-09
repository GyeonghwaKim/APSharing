package com.APSharing.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;


@Service
public class ForecastService {

    public <T> T parseJson(String json, Class<T> valueType) {
        T object = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            object = mapper.readValue(json, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }


}