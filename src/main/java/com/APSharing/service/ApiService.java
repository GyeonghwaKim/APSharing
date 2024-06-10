package com.APSharing.service;

import com.APSharing.vo.LunPh;
import com.APSharing.vo.LunPhItem;
import com.APSharing.vo.LunPhItems;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;


@Service
public class ApiService {

    public <T> T parseJson(String json, Class<T> valueType) {
        T object = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            object = mapper.readValue(json, valueType);

            if(object instanceof LunPhItems){
                getLunPhName(((LunPhItems) object).getLunPhItem());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    private LunPhItem getLunPhName(LunPhItem item){
        String name= LunPh.fromDay(item.getLunAge());
        item.setLunPhName(name);

        return item;
    }




}