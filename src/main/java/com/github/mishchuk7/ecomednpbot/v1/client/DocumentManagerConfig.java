package com.github.mishchuk7.ecomednpbot.v1.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DocumentManagerConfig {


    private final String baseUrl;
    private final String apiKey;

    @Autowired
    public DocumentManagerConfig(@Value("${config.baseUrl}") String baseUrl, @Value("${config.apiKey}") String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

}
