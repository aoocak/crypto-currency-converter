
package com.zoopluscase.cryptocurrencyconverter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "country_code",
        "languages",
})

@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class GeoLocation {
    @JsonProperty("languages")
    public String languages;

    @JsonProperty("country_code")
    public String countryCode;
}