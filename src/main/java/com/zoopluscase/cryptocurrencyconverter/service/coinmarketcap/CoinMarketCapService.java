package com.zoopluscase.cryptocurrencyconverter.service.coinmarketcap;

import com.zoopluscase.cryptocurrencyconverter.client.coinmarketcap.CoinMarketCapClient;
import com.zoopluscase.cryptocurrencyconverter.client.coinmarketcap.CoinMarketCapClientException;
import com.zoopluscase.cryptocurrencyconverter.model.CoinMarketCapResponseDTO;
import com.zoopluscase.cryptocurrencyconverter.model.Currency;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoinMarketCapService {

    private final CoinMarketCapClient coinMarketCapClient;


    public CoinMarketCapService(CoinMarketCapClient coinMarketCapClient) {
        this.coinMarketCapClient = coinMarketCapClient;
    }

    public Double convert(String coinSymbol, String fiatSymbol) throws URISyntaxException, IOException, CoinMarketCapServiceException, CoinMarketCapClientException {
        if (StringUtils.isEmpty(coinSymbol) || StringUtils.isEmpty(fiatSymbol)) {
            throw new CoinMarketCapServiceException("coin symbol or fiat symbol are null");
        }

        List<NameValuePair> parameters = getParameters(coinSymbol, fiatSymbol);

        CoinMarketCapResponseDTO coinMarketCapResponseDTO = coinMarketCapClient.convertCoinToFiat(parameters);

        Currency currency = coinMarketCapResponseDTO.getData().getQuote().getCurrencyMap().get(fiatSymbol);
        return currency.getPrice();
    }

    private List<NameValuePair> getParameters(String coinSymbol, String fiatSymbol) {
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("symbol", coinSymbol));
        parameters.add(new BasicNameValuePair("convert", fiatSymbol));
        parameters.add(new BasicNameValuePair("amount", "1"));
        return parameters;
    }
}
