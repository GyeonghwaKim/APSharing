package com.APSharing.vo;

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

        String locdate=item.getLocdate().trim();

        if(locdate.length()==6){
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMM");
            YearMonth yearMonth = YearMonth.parse(locdate, inputFormatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
            item.setFormatLocdate(yearMonth.format(outputFormatter));

            log.info(item.getFormatLocdate());
        } else if (locdate.length()==8) {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate date = LocalDate.parse(locdate, inputFormatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            item.setFormatLocdate(date.format(outputFormatter));
        }else{
            throw new IllegalArgumentException("Invalid date format");
        }


    }

}