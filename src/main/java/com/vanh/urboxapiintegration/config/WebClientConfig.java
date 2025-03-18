package com.vanh.urboxapiintegration.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${api.base-url}")
    private String baseUrl;

    @Value("${api.app-id}")
    private String appId;

    @Value("${api.app-secret}")
    private String appSecret;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("app_id", appId)
                .defaultHeader("app_secret", appSecret)
                .build();
    }
}