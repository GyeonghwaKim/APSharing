package com.APSharing.vo;

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
public class DivisionsItems {

    @JsonProperty("item")
    private List<DivisionsItem> divisionsItems;

    public DivisionsItems(@JsonProperty("response")JsonNode node) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();

        JsonNode itemNode=node.findValue("item");
        this.divisionsItems = Arrays
                .stream(objectMapper.treeToValue(itemNode, DivisionsItem[].class))
                .toList();
    }
}
