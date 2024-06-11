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

//            if(object instanceof LunPhItems){
//                getLunPhName(((LunPhItems) object).getLunPhItem());
//                getLunPhDescription(((LunPhItems) object).getLunPhItem());
//                getLunPhEmoji(((LunPhItems) object).getLunPhItem());
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

//    private void getLunPhName(LunPhItem item){
//        String name= LunPh.fromDayToName(item.getLunAge());
//        item.setLunPhName(name);
//    }
//
//    private void getLunPhDescription(LunPhItem item){
//        String description=LunPh.fromDayToDescription(item.getLunAge());
//        item.setDescription(description);
//    }
//
//    private void getLunPhEmoji(LunPhItem item){
//        String emoji=LunPh.fromDayToEmoji(item.getLunAge());
//        item.setEmoji(emoji);
//    }


}