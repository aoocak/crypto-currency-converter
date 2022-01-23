package com.zoopluscase.cryptocurrencyconverter.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoopluscase.cryptocurrencyconverter.client.ipapi.IpApiClient;
import com.zoopluscase.cryptocurrencyconverter.client.ipapi.IpApiClientException;
import com.zoopluscase.cryptocurrencyconverter.model.GeoLocation;
import com.zoopluscase.cryptocurrencyconverter.service.ipapi.IpApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class IpApiServiceTest {

    public static final String CA_IP_ADDRESS = "192.206.151.131";
    @Value("${ip-api.url}") String ipApiEndpoint;
    @Value("${ip-api.path.geo-location.format}") String geoLocationFormatPath;
    private IpApiService ipApiService;

    @BeforeEach
    public void setUp() {
        IpApiClient ipApiClient = new IpApiClient(ipApiEndpoint, geoLocationFormatPath, new ObjectMapper());
        ipApiService = new IpApiService(ipApiClient);
    }

    @Test
    public void shouldSuccess_ipApiCall() throws URISyntaxException, IOException, IpApiClientException {
        GeoLocation geoLocation = ipApiService.getGeoLocation(CA_IP_ADDRESS);
        assertEquals(geoLocation.getCountryCode(), "CA");
        assertEquals(geoLocation.getLanguages(), "en-CA,fr-CA,iu");
    }

    @Test
    public void shouldThrow_whenIpAddressInvalid() throws URISyntaxException, IOException, IpApiClientException {
        assertThrows(IpApiClientException.class, () ->
                ipApiService.getGeoLocation(CA_IP_ADDRESS + "1"));
    }
}