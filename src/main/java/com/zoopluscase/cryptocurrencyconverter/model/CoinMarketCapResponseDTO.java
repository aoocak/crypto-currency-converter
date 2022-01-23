
package com.zoopluscase.cryptocurrencyconverter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
@JsonPropertyOrder({
    "status",
    "data"
})
@Generated("jsonschema2pojo")
@lombok.Data
public class CoinMarketCapResponseDTO {

    @JsonProperty("status")
    private Status status;
    @JsonProperty("data")
    private Data data;

}
