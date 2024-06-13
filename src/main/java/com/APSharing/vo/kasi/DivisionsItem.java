package com.APSharing.vo.kasi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DivisionsItem {

    @JsonProperty("dateName")
    private String dateName;

    @JsonProperty("locdate")
    private String locdate;

    private String formatLocdate;
}
