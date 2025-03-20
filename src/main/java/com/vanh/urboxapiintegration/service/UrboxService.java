package com.vanh.urboxapiintegration.service;

import com.vanh.urboxapiintegration.dto.UrboxResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;

@Service
public class UrboxService {
    @Value("${app.secret}")
    private String app_secret;

    @Value("${app.id}")
    private int app_id;

    @Value("${base.url}")
    private String base_url;

    private final WebClient webClient;

    public UrboxService(WebClient webClient) {
        this.webClient = webClient;
    }

    public <T> Mono<UrboxResponse<T>> callUrboxApi(String endpoint, Map<String, Object> params, Class<T> responseType) {
        return webClient.get()
                .uri(uriBuilder -> {
                    uriBuilder.path(endpoint)
                            .queryParam("app_secret", app_secret)
                            .queryParam("app_id", app_id);
                    if (params != null) {
                        params.forEach(uriBuilder::queryParam);
                    }
                    return uriBuilder.build();
                })
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<UrboxResponse<T>>() {
                }).timeout(Duration.ofSeconds(60)).onErrorResume(throwable -> {
                    return Mono.just(new UrboxResponse<>());
                });
    }
}