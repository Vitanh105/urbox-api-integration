package com.vanh.urboxapiintegration.service;

import com.vanh.urboxapiintegration.dto.response.GiftDetailResponse;
import com.vanh.urboxapiintegration.dto.response.UrboxResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class GiftDetailService extends UrboxService {
    public GiftDetailService(WebClient webClient) {
        super(webClient);
    }

    public Mono<UrboxResponse<GiftDetailResponse>> getGiftDetail(String id,
                                                                 String lang) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        if (lang != null) params.put("lang", lang);
        return callGetApi("/4.0/gift/detail", params, GiftDetailResponse.class);
    }
}
