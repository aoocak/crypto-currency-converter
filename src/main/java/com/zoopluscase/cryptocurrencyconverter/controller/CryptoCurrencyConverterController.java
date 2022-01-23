package com.zoopluscase.cryptocurrencyconverter.controller;

import com.zoopluscase.cryptocurrencyconverter.client.coinmarketcap.CoinMarketCapClientException;
import com.zoopluscase.cryptocurrencyconverter.client.ipapi.IpApiClientException;
import com.zoopluscase.cryptocurrencyconverter.model.ConvertRequestDTO;
import com.zoopluscase.cryptocurrencyconverter.model.ConvertResponseDTO;
import com.zoopluscase.cryptocurrencyconverter.service.CryptoCurrencyConversionService;
import com.zoopluscase.cryptocurrencyconverter.service.coinmarketcap.CoinMarketCapServiceException;
import com.zoopluscase.cryptocurrencyconverter.service.ipapi.IpApiValidationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;

@Controller
public class CryptoCurrencyConverterController {

    private final CryptoCurrencyConversionService cryptoCurrencyConversionService;

    public CryptoCurrencyConverterController(CryptoCurrencyConversionService cryptoCurrencyConversionService) {
        this.cryptoCurrencyConversionService = cryptoCurrencyConversionService;
    }

    @GetMapping("/convert")
    public String convert(@ModelAttribute ConvertRequestDTO requestDTO, HttpServletRequest httpServletRequest, Model model) {

        model.addAttribute("requestDTO", requestDTO);
        ConvertResponseDTO responseDTO = new ConvertResponseDTO();

        // landing
        if (requestDTO.getCoinSymbol() == null) {
            model.addAttribute("responseDTO", responseDTO);
            return "convert";
        }

        try {
            responseDTO = cryptoCurrencyConversionService.convertCryptoCurrency(requestDTO, httpServletRequest);
            model.addAttribute("responseDTO", responseDTO);
        } catch (IpApiClientException | CoinMarketCapClientException | IpApiValidationException | URISyntaxException | IOException | CoinMarketCapServiceException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "convert";
    }
}
