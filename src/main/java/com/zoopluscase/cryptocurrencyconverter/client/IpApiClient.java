package com.zoopluscase.cryptocurrencyconverter.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoopluscase.cryptocurrencyconverter.model.GeoLocation;
import org.apache.http.HttpEntity;
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

@Service
public class IpApiClient {

    private final String ipApiEndpoint;
    private final String geoLocationFormatPath;
    private final ObjectMapper objectMapper;

    public IpApiClient(@Value("${ip-api.url}") String ipApiEndpoint,
                       @Value("${ip-api.path.geo-location.format}") String geoLocationFormatPath,
                       ObjectMapper objectMapper) {
        this.ipApiEndpoint = ipApiEndpoint;
        this.geoLocationFormatPath = geoLocationFormatPath;
        this.objectMapper = objectMapper;
    }


    public GeoLocation getGeoLocation(String ipAddress) throws URISyntaxException, IOException, IpApiClientException {

        URIBuilder query = new URIBuilder(ipApiEndpoint);
        query.setPath(ipAddress + geoLocationFormatPath);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(query.build());

        CloseableHttpResponse response = client.execute(request);

        String response_content;
        try {
            HttpEntity entity = response.getEntity();
            response_content = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }

        GeoLocation geoLocation = objectMapper.readValue(response_content, GeoLocation.class);

        if (geoLocation.isError()) {
            throw new IpApiClientException(geoLocation.getReason());
        }

        return geoLocation;
    }
}
