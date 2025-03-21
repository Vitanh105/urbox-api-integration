package com.vanh.urboxapiintegration.service;

import com.vanh.urboxapiintegration.dto.response.UrboxResponse;
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
    protected String app_secret;

    @Value("${app.id}")
    protected int app_id;

    @Value("${base.url}")
    protected String base_url;

    protected final WebClient webClient;

    public UrboxService(WebClient webClient) {
        this.webClient = webClient;
    }

    public <T> Mono<UrboxResponse<T>> callGetApi(String endpoint, Map<String, Object> params, Class<T> responseType) {
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

    public <T, R> Mono<UrboxResponse<R>> callPostApi(String endpoint, T requestBody, String signature, Class<R> responseType) {
        return webClient.post()
                .uri(base_url + endpoint)
                .header("Content-Type", "application/json")
                .header("Signature", signature)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<UrboxResponse<R>>() {
                })
                .timeout(Duration.ofSeconds(60))
                .onErrorResume(throwable -> Mono.just(new UrboxResponse<>()));
    }
}