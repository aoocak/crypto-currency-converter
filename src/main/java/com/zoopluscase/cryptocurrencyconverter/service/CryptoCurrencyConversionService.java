package com.zoopluscase.cryptocurrencyconverter.service;


import com.zoopluscase.cryptocurrencyconverter.client.IpApiClient;
import com.zoopluscase.cryptocurrencyconverter.model.ConvertRequestDTO;
import com.zoopluscase.cryptocurrencyconverter.model.ConvertResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class CryptoCurrencyConversionService {

    //injects clients
    private final IpApiClient ipApiClient;

    public CryptoCurrencyConversionService(IpApiClient ipApiClient) {
        this.ipApiClient = ipApiClient;
    }

    public ConvertResponseDTO convertCryptoCurrency(ConvertRequestDTO requestDTO) {
        //fetches geoaddress from client with ip
        //fetches cprytoprice from client with coin-fiat info
        return null;
    }
}
