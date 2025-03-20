package com.vanh.urboxapiintegration.service;

import com.vanh.urboxapiintegration.dto.response.CategoryResponse;
import com.vanh.urboxapiintegration.dto.response.UrboxResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class CategoryService extends UrboxService {
    public CategoryService(WebClient webClient) {
        super(webClient);
    }

    public Mono<UrboxResponse<CategoryResponse>> getCategory(Integer parent_id, String lang) {
        Map<String, Object> params = new HashMap<>();
        if (parent_id != null) params.put("parent_id", parent_id);
        if (lang != null) params.put("lang", lang);
        return callGetApi("/2.0/category/catbyparent", params, CategoryResponse.class);
    }
}
