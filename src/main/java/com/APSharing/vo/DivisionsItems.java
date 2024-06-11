package com.APSharing.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class DivisionsItems {

    @JsonProperty("item")
    private List<DivisionsItem> divisionsItems;

    public DivisionsItems(@JsonProperty("response")JsonNode node) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();

        JsonNode itemNode=node.findValue("item");
        List<DivisionsItem> divisionsItemList=Arrays
                .stream(objectMapper.treeToValue(itemNode, DivisionsItem[].class))
                .toList();

        divisionsItemList.forEach(this::setFormatLocName);

        this.divisionsItems =divisionsItemList;
    }

    private void setFormatLocName(DivisionsItem item){

        String locdate=item.getLocdate();

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(locdate, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        item.setFormatLocdate(date.format(outputFormatter));

    }
}
