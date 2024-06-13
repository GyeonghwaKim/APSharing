package com.APSharing.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AstroEventItem {

    //천문현상
    @JsonProperty("astroEvent")
    private String astroEvent;
    //천문현상명
    @JsonProperty("astroTitle")
    private String astroTitle;

    //천문현상시각
    @JsonProperty("astroTime")
    private String astroTime;

    //천문현상 날짜
    @JsonProperty("locdate")
    private String locdate;

    private String formatLocdate;


}