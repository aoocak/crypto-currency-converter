
package com.zoopluscase.cryptocurrencyconverter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "price",
    "last_updated"
})
@Generated("jsonschema2pojo")
@Data
public class Currency {

    @JsonProperty("price")
    private Double price;
    @JsonProperty("last_updated")
    private String lastUpdated;
}
