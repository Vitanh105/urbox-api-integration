package com.vanh.urboxapiintegration.service;

import com.vanh.urboxapiintegration.dto.response.CartTransactionResponse;
import com.vanh.urboxapiintegration.dto.response.UrboxResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartTransactionService extends UrboxService{
    public CartTransactionService(WebClient webClient) {
        super(webClient);
    }
    public Mono<UrboxResponse<CartTransactionResponse>> getCartByTransaction(String transaction_id) {
        Map<String, Object> params = new HashMap<>();
        params.put("transaction_id", transaction_id);
        return callGetApi("/2.0/cart/getByTransaction", params, CartTransactionResponse.class);
    }
}
