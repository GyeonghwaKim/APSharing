package com.APSharing.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Apod {

    @JsonProperty("date")
    private String date;

    @JsonProperty("explanation")
    private String explanation;

    @JsonProperty("media_type")
    private String media_type;

    @JsonProperty("hdurl")
    private String hdurl;

    @JsonProperty("url")
    private String url;

    @JsonProperty("title")
    private String title;

    @JsonProperty("service_version")
    private String service_version;
}
