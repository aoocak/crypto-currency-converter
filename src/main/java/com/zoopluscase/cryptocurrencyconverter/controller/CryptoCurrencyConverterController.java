package com.zoopluscase.cryptocurrencyconverter.controller;

import com.zoopluscase.cryptocurrencyconverter.model.ConvertRequestDTO;
import com.zoopluscase.cryptocurrencyconverter.service.CryptoCurrencyConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CryptoCurrencyConverterController {

    private final CryptoCurrencyConversionService cryptoCurrencyConversionService;

    public CryptoCurrencyConverterController(CryptoCurrencyConversionService cryptoCurrencyConversionService) {
        this.cryptoCurrencyConversionService = cryptoCurrencyConversionService;
    }

    @GetMapping("/convert")
    public String convert(@ModelAttribute ConvertRequestDTO requestDTO, HttpServletRequest httpServletRequest, Model model) {
        return "";
    }
}
