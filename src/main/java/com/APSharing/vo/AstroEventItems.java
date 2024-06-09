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
public class AstroEventItems {
    @JsonProperty("item")
    private List<AstroEventItem> astroEventItems;

    @JsonCreator
    public AstroEventItems(@JsonProperty("response") JsonNode node) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();

        JsonNode itemNode= node.findValue("item");
        this.astroEventItems = Arrays
                .stream(objectMapper.treeToValue(itemNode, AstroEventItem[].class))
                .toList();
    }
}