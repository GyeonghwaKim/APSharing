package com.APSharing.vo.kasi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Data
@AllArgsConstructor
public class AstroEventItems {
    @JsonProperty("item")
    private List<AstroEventItem> astroEventItems;

    @JsonCreator
    public AstroEventItems(@JsonProperty("response") JsonNode node) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();

        JsonNode itemNode= node.findValue("item");
        List<AstroEventItem> astroEventItemList=Arrays
                .stream(objectMapper.treeToValue(itemNode, AstroEventItem[].class))
                .toList();

        astroEventItemList.forEach(this::setFormatLocDate);

        this.astroEventItems =astroEventItemList;
    }

    private void setFormatLocDate(AstroEventItem item){
        DateTimeFormatter inputFormatter,outputFormatter;
        YearMonth yearMonth;
        LocalDate yearMonthDay;

        String locdate=item.getLocdate().trim();

        if(locdate.length()==6){

            inputFormatter = DateTimeFormatter.ofPattern("yyyyMM");
            outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM");

            yearMonth = YearMonth.parse(locdate, inputFormatter);
            item.setFormatLocdate(yearMonth.format(outputFormatter));

        } else if (locdate.length()==8) {
            inputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            yearMonthDay= LocalDate.parse(locdate, inputFormatter);
            item.setFormatLocdate(yearMonthDay.format(outputFormatter));
        }else{
            throw new IllegalArgumentException("Invalid date format");
        }


    }

}