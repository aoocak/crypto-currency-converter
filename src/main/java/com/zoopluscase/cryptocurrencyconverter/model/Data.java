
package com.zoopluscase.cryptocurrencyconverter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "symbol",
    "name",
    "amount",
    "last_updated",
    "quote"
})
@Generated("jsonschema2pojo")
@lombok.Data
public class Data {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("name")
    private String name;
    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("last_updated")
    private String lastUpdated;
    @JsonProperty("quote")
    private Quote quote;

}
