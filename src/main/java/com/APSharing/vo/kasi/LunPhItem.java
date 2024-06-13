package com.APSharing.vo.kasi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LunPhItem {

    @JsonProperty("lunAge")
    private int lunAge;

    @JsonProperty("solYear")
    private String solYear;

    @JsonProperty("solMonth")
    private String solMonth;

    @JsonProperty("solWeek")
    private String solWeek;

    @JsonProperty("solDay")
    private String solDay;

    private String description;

    private String emoji;

    private LunPh value;

}
