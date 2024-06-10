package com.APSharing.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
public class LunPhItems {

    @JsonProperty("item")
    private LunPhItem lunPhItem;

    @JsonCreator
    public LunPhItems(@JsonProperty("response") JsonNode node) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode itemNode= node.findValue("item");
        this.lunPhItem = objectMapper.treeToValue(itemNode, LunPhItem.class);
    }

}
