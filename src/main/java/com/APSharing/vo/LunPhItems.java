package com.APSharing.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
public class LunPhItems {

    @JsonProperty("item")
    private LunPhItem lunPhItem;

    @JsonCreator
    public LunPhItems(@JsonProperty("response") JsonNode node) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode itemNode= node.findValue("item");
        LunPhItem item=objectMapper.treeToValue(itemNode, LunPhItem.class);
        setLunPhValue(item);
        setLunPhDescription(item);
        setLunPhEmoji(item);
        this.lunPhItem = item;
    }

    private void setLunPhValue(LunPhItem item){
        LunPh value= LunPh.fromDayToValue(item.getLunAge());
        item.setValue(value);
    }

    private void setLunPhDescription(LunPhItem item){
        String description=LunPh.fromDayToDescription(item.getLunAge());
        item.setDescription(description);
    }

    private void setLunPhEmoji(LunPhItem item){
        String emoji=LunPh.fromDayToEmoji(item.getLunAge());
        item.setEmoji(emoji);
    }


}
