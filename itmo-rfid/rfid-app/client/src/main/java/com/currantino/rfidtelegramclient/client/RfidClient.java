package com.currantino.rfidtelegramclient.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Component
public class RfidClient {
    private final RestTemplate rest;

    public RfidClient(
            @Value("${rfid.server.url}")
            String serverUrl,
            RestTemplateBuilder builder
    ) {
        this.rest = builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                .requestFactory(HttpComponentsClientHttpRequestFactory.class)
                .build();
    }

    public String hello() {

        ResponseEntity<Object> statServerResponse;
        return rest.exchange(RequestEntity.get("/hello").build(), String.class)
                .getBody();
    }


}
