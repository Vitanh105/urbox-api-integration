package com.vanh.urboxapiintegration.service;

import com.vanh.urboxapiintegration.dto.response.UrboxResponse;
import com.vanh.urboxapiintegration.dto.response.BrandResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class BrandService extends UrboxService {

    public BrandService(WebClient webClient) {
        super(webClient);
    }

    public Mono<UrboxResponse<BrandResponse>> getBrand(Integer cat_id, Integer per_page, Integer page_no) {
        Map<String, Object> params = new HashMap<>();
        if (cat_id != null) params.put("cat_id", cat_id);
        if (per_page != null) params.put("per_page", per_page);
        if (page_no != null) params.put("page_no", page_no);
        return callGetApi("/4.0/gift/brand", params, BrandResponse.class);
    }
}