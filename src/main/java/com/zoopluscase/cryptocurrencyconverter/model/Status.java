
package com.zoopluscase.cryptocurrencyconverter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "timestamp",
    "error_code",
    "error_message",
    "elapsed",
    "credit_count",
    "notice"
})
@Generated("jsonschema2pojo")
@Data
public class Status {

    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("error_code")
    private Integer errorCode;
    @JsonProperty("error_message")
    private String errorMessage;
    @JsonProperty("elapsed")
    private Integer elapsed;
    @JsonProperty("credit_count")
    private Integer creditCount;
    @JsonProperty("notice")
    private Object notice;

}
