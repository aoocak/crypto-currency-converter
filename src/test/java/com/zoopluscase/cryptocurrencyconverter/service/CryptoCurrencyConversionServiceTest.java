package com.zoopluscase.cryptocurrencyconverter.service;

import com.zoopluscase.cryptocurrencyconverter.model.ConvertRequestDTO;
import com.zoopluscase.cryptocurrencyconverter.model.ConvertResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void shouldSuccess() {
        ConvertRequestDTO requestDTO = new ConvertRequestDTO();
        requestDTO.setCoinSymbol("TRY");
        requestDTO.setIpAddress("212.64.216.78"); //Turkey
        ConvertResponseDTO responseDTO = cryptoCurrencyConversionService.convertCryptoCurrency(requestDTO);
        assertEquals(responseDTO.getPrice(), "₺1,00");
    }
}