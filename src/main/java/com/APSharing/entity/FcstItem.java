package com.APSharing.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FcstItem {

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

    //순번
    @JsonProperty("sec")
    private int sec;

    //비고
    @JsonProperty("remakrs")
    private String remkars;


}