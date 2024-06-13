package com.APSharing.vo.kasi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        List<DivisionsItem> divisionsItemList=Arrays
                .stream(objectMapper.treeToValue(itemNode, DivisionsItem[].class))
                .toList();

        divisionsItemList.forEach(this::setFormatLocDate);

        this.divisionsItems =divisionsItemList;
    }

    private void setFormatLocDate(DivisionsItem item){

        DateTimeFormatter inputFormatter,outputFormatter;
        LocalDate yearMonthDay;

        String locdate=item.getLocdate().trim();

        inputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        yearMonthDay = LocalDate.parse(locdate, inputFormatter);
        item.setFormatLocdate(yearMonthDay.format(outputFormatter));

    }
}
