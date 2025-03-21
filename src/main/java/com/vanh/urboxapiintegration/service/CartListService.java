package com.vanh.urboxapiintegration.service;

import com.vanh.urboxapiintegration.dto.response.CartListResponse;
import com.vanh.urboxapiintegration.dto.response.UrboxResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartListService extends UrboxService {
    public CartListService(WebClient webClient) {
        super(webClient);
    }

    public Mono<UrboxResponse<CartListResponse>> getCartList(String site_user_id, String campaign_code, String startDate, String endDate, String transaction_id) {
        Map<String, Object> params = new HashMap<>();
        params.put("site_user_id", site_user_id);
        if (campaign_code != null) params.put("campaign_code", campaign_code);
        if (startDate != null) params.put("startDate", startDate);
        if (endDate != null) params.put("endDate", endDate);
        if (transaction_id != null) params.put("transaction_id", transaction_id);
        return callGetApi("/2.0/cart/getlist", params, CartListResponse.class);
    }
}
