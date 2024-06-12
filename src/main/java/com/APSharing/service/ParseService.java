package com.APSharing.service;

import com.APSharing.vo.LunPh;
import com.APSharing.vo.LunPhItem;
import com.APSharing.vo.LunPhItems;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;


@Service
public class ParseService {

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