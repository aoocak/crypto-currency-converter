package com.zoopluscase.cryptocurrencyconverter.service;

import com.zoopluscase.cryptocurrencyconverter.client.coinmarketcap.CoinMarketCapClientException;
import com.zoopluscase.cryptocurrencyconverter.client.ipapi.IpApiClientException;
import com.zoopluscase.cryptocurrencyconverter.model.ConvertRequestDTO;
import com.zoopluscase.cryptocurrencyconverter.model.ConvertResponseDTO;
import com.zoopluscase.cryptocurrencyconverter.model.GeoLocation;
import com.zoopluscase.cryptocurrencyconverter.service.coinmarketcap.CoinMarketCapService;
import com.zoopluscase.cryptocurrencyconverter.service.coinmarketcap.CoinMarketCapServiceException;
import com.zoopluscase.cryptocurrencyconverter.service.ipapi.IpApiService;
import com.zoopluscase.cryptocurrencyconverter.service.ipapi.IpApiValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class CryptoCurrencyConversionServiceTest {

    private CryptoCurrencyConversionService cryptoCurrencyConversionService;

    @Mock
    private CoinMarketCapService coinMarketCapService;

    @Mock
    private IpApiService ipApiService;


    @BeforeEach
    public void setUp() {
        cryptoCurrencyConversionService = new CryptoCurrencyConversionService(coinMarketCapService, ipApiService);
    }

    @Test
    public void shouldSuccess() throws IpApiClientException, CoinMarketCapClientException, URISyntaxException, IOException, CoinMarketCapServiceException, IpApiValidationException {
        ConvertRequestDTO requestDTO = new ConvertRequestDTO();
        requestDTO.setCoinSymbol("BTC");
        requestDTO.setIpAddress("0.0.0.0");

        GeoLocation geoLocation = new GeoLocation();
        geoLocation.setCountryCode("TR");
        geoLocation.setLanguages("tr");

        when(ipApiService.getGeoLocation(requestDTO.getIpAddress())).thenReturn(geoLocation);
        when(coinMarketCapService.convert(requestDTO.getCoinSymbol(), "TRY")).thenReturn(1d);

        ConvertResponseDTO responseDTO = cryptoCurrencyConversionService.convertCryptoCurrency(requestDTO, null);

        assertEquals(responseDTO.getPrice(), "₺1,00");
    }
}