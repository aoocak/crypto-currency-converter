package com.zoopluscase.cryptocurrencyconverter.service.ipapi;

import com.zoopluscase.cryptocurrencyconverter.client.ipapi.IpApiClient;
import com.zoopluscase.cryptocurrencyconverter.client.ipapi.IpApiClientException;
import com.zoopluscase.cryptocurrencyconverter.model.GeoLocation;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class IpApiService {

    private final IpApiClient ipApiClient;

    public IpApiService(IpApiClient ipApiClient) {
        this.ipApiClient = ipApiClient;
    }

    public GeoLocation getGeoLocation(String ipAddress) throws URISyntaxException, IOException, IpApiClientException {
        GeoLocation geoLocation = ipApiClient.getGeoLocation(ipAddress);
        return geoLocation;
    }
}
