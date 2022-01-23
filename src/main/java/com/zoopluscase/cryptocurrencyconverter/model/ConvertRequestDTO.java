package com.zoopluscase.cryptocurrencyconverter.model;

import lombok.Data;

@Data
public class ConvertRequestDTO {

    private String coinSymbol;
    private String ipAddress;
}
