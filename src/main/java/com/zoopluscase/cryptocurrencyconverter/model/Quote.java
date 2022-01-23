
package com.zoopluscase.cryptocurrencyconverter.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("jsonschema2pojo")
@Data
public class Quote {

    private Map<String, Currency> currencyMap = new HashMap<>();

    @JsonAnySetter
    public void setCurrency(String fiatSymbol, Currency currency) {
        this.currencyMap.put(fiatSymbol, currency);
    }
}
