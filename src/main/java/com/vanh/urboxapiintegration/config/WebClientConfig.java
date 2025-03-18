package com.vanh.urboxapiintegration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://sandapi.urbox.dev")
                .defaultHeader("app_secret", "your-app-secret") //chưa biết
                .defaultHeader("app_id", "your-app-id") //chưa biết
                .build();
    }
}
