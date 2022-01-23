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
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CryptoCurrencyConversionService {

    private static final String IPV4_PATTERN = "^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$";
    private static final Pattern pattern = Pattern.compile(IPV4_PATTERN);

    private final CoinMarketCapService coinMarketCapService;
    private final IpApiService ipApiService;

    public CryptoCurrencyConversionService(CoinMarketCapService coinMarketCapService, IpApiService ipApiService) {
        this.coinMarketCapService = coinMarketCapService;
        this.ipApiService = ipApiService;
    }


    public ConvertResponseDTO convertCryptoCurrency(ConvertRequestDTO requestDTO, HttpServletRequest httpServletRequest) throws IpApiClientException, URISyntaxException, IOException, CoinMarketCapClientException, CoinMarketCapServiceException, IpApiValidationException {

        String ipAddress = getIpAddress(requestDTO, httpServletRequest);

        if (!isIpAddressValid(ipAddress)) {
            throw new IpApiValidationException("Ip address is invalid, ip address: " + ipAddress);
        }

        GeoLocation geoLocation = ipApiService.getGeoLocation(ipAddress);

        NumberFormat fiatFormatter = NumberFormat.getCurrencyInstance(new Locale(geoLocation.getLanguages(), geoLocation.getCountryCode()));

        String fiatSymbol = fiatFormatter.getCurrency().getCurrencyCode();

        Double unformattedPrice = coinMarketCapService.convert(requestDTO.getCoinSymbol(), fiatSymbol);


        ConvertResponseDTO responseDTO = new ConvertResponseDTO();
        responseDTO.setPrice(fiatFormatter.format(unformattedPrice));

        return responseDTO;
    }

    private boolean isIpAddressValid(String ipAddress) {
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }

    private String getIpAddress(ConvertRequestDTO requestDTO, HttpServletRequest httpServletRequest) {
        String ipAddress;
        if (requestDTO.getIpAddress() == null) {
            ipAddress = httpServletRequest.getRemoteAddr();
        } else {
            ipAddress = requestDTO.getIpAddress();
        }
        return ipAddress;
    }
}
