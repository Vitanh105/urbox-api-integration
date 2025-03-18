package com.vanh.urboxapiintegration.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.vanh.urboxapiintegration.dto.brand.BrandResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.View;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BrandService {

    private static final Logger logger = LoggerFactory.getLogger(BrandService.class);

    private final WebClient webClient;

    @Value("${base-url}")
    private String baseUrl;

    // Cache để lưu parent_cat_id
    private final Cache<String, Set<String>> parentCatIdCache = Caffeine.newBuilder()
            .maximumSize(1000)
            .build();

    public BrandService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<BrandResponse> getBrands(String cat_id, Integer per_page, Integer page_no) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        if (cat_id != null) {
            // Xem trong cache có mã danh mục cha hay không trước
            Set<String> cachedParentCatIds = parentCatIdCache.getIfPresent("parent_cat_id");
            if (cachedParentCatIds != null && !cachedParentCatIds.contains(cat_id)) {
                logger.debug("cat_id {} not found in cache -> call API", cat_id);
            } else {
                params.add("cat_id", cat_id);
            }
        }
        if (per_page != null) params.add("per_page", per_page.toString());
        if (page_no != null) params.add("page_no", page_no.toString());

        logger.debug("Sending request to {} with params: {}", baseUrl + "/4.0/gift/brand", params);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/4.0/gift/brand")
                        .queryParams(params)
                        .build())
                .retrieve()
                .bodyToMono(BrandResponse.class)
                .doOnSuccess(response -> {
                    logger.debug("Received response: done={}, status={}, data={}", response.getDone(), response.getStatus(), response.getData());
                    if (response.getData() != null && response.getData().getItems() != null) {
                        Set<String> parentCatId = parentCatIdCache.getIfPresent("parent_cat_id");
                        if (parentCatId == null) parentCatId = new HashSet<>();
                        Set<String> finalParentCatId = parentCatId;
                        response.getData().getItems().forEach(item -> finalParentCatId.add(item.getParent_cat_id()));
                        parentCatIdCache.put("parent_cat_id", parentCatId);
                    }
                })
                .doOnError(error -> logger.error("Error calling API: {}", error.getMessage()));
    }

    // Phương thức để lấy danh sách parent_cat_id từ cache
    public Set<String> getCachedParentCatIds() {
        return parentCatIdCache.getIfPresent("parent_cat_id");
    }
}