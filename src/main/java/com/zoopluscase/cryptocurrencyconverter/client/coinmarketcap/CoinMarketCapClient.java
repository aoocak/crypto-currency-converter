package com.zoopluscase.cryptocurrencyconverter.client.coinmarketcap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoopluscase.cryptocurrencyconverter.model.CoinMarketCapResponseDTO;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class CoinMarketCapClient {

    private static final String APPLICATION_JSON_HEADER = "application/json";
    private static final String API_KEY_KEY = "X-CMC_PRO_API_KEY";
    public static final int SUCCESS_CLIENT_CODE = 0;
    private final String coinMarketCapConversionEndpoint;
    private final String coinMarketCapApiKey;
    private final ObjectMapper objectMapper;

    public CoinMarketCapClient(@Value("${coin-market-cap.url.price-conversion}") String coinMarketCapConversionEndpoint,
                               @Value("${coin-market-cap.api-key}") String coinMarketCapApiKey,
                               ObjectMapper objectMapper) {
        this.coinMarketCapConversionEndpoint = coinMarketCapConversionEndpoint;
        this.coinMarketCapApiKey = coinMarketCapApiKey;
        this.objectMapper = objectMapper;
    }

    public CoinMarketCapResponseDTO convertCoinToFiat(List<NameValuePair> parameters) throws URISyntaxException, IOException, CoinMarketCapClientException {

        URIBuilder query = new URIBuilder(coinMarketCapConversionEndpoint);
        query.addParameters(parameters);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(query.build());

        request.setHeader(HttpHeaders.ACCEPT, APPLICATION_JSON_HEADER);
        request.addHeader(API_KEY_KEY, coinMarketCapApiKey);

        CloseableHttpResponse response = client.execute(request);

        HttpEntity entity = response.getEntity();
        String response_content = EntityUtils.toString(entity);
        EntityUtils.consume(entity);

        response.close();


        CoinMarketCapResponseDTO coinMarketCapResponseDTO = objectMapper.readValue(response_content, CoinMarketCapResponseDTO.class);

        if (SUCCESS_CLIENT_CODE != coinMarketCapResponseDTO.getStatus().getErrorCode()) {
            throw new CoinMarketCapClientException(coinMarketCapResponseDTO.getStatus().getErrorMessage());
        }

        return coinMarketCapResponseDTO;
    }
}