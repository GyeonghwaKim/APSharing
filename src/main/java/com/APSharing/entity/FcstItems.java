package com.APSharing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.List;

@Data
@JsonDeserialize(using = FcstItemDeserializer.class)
public class FcstItems {
    @JsonProperty("item")
    private List<FcstItem> fcstItems;

    public FcstItems(List<FcstItem> fcstItems) {
        this.fcstItems = fcstItems;
    }
}