package com.zoopluscase.cryptocurrencyconverter.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoopluscase.cryptocurrencyconverter.client.coinmarketcap.CoinMarketCapClient;
import com.zoopluscase.cryptocurrencyconverter.client.coinmarketcap.CoinMarketCapClientException;
import com.zoopluscase.cryptocurrencyconverter.service.coinmarketcap.CoinMarketCapService;
import com.zoopluscase.cryptocurrencyconverter.service.coinmarketcap.CoinMarketCapServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CoinMarketCapServiceTest {

    private CoinMarketCapService coinMarketCapService;
    @Value("${coin-market-cap.url.price-conversion}") String coinMarketCapConversionEndpoint;
    @Value("${coin-market-cap.api-key}") String coinMarketCapApiKey;

    @BeforeEach
    public void setUp() {
        CoinMarketCapClient coinMarketCapClient = new CoinMarketCapClient(coinMarketCapConversionEndpoint, coinMarketCapApiKey, new ObjectMapper());
        coinMarketCapService = new CoinMarketCapService(coinMarketCapClient);
    }

    @Test
    public void shouldSuccess_coinMarketCapClientCall() throws URISyntaxException, IOException, CoinMarketCapClientException, CoinMarketCapServiceException {
        Double price = coinMarketCapService.convert("BTC", "BTC");
        assertEquals(1d, price);
    }

    @Test
    public void shouldThrow_whenCoinSymbolIsNull() {
        assertThrows(CoinMarketCapServiceException.class, () ->
                coinMarketCapService.convert("", "USD"));
    }
}