package com.vanh.urboxapiintegration.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanh.urboxapiintegration.dto.request.CartPayVoucherRequest;
import com.vanh.urboxapiintegration.dto.response.CartPayVoucherResponse;
import com.vanh.urboxapiintegration.dto.response.UrboxResponse;
import com.vanh.urboxapiintegration.util.SignatureUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartPayVoucherService extends UrboxService {
    private final SignatureUtil signatureUtil;
    private final ObjectMapper objectMapper;

    @Value("${private.key.path}")
    private String privateKeyPath;

    public CartPayVoucherService(WebClient webClient, SignatureUtil signatureUtil, ObjectMapper objectMapper) {
        super(webClient);
        this.signatureUtil = signatureUtil;
        this.objectMapper = objectMapper;
    }

    public Mono<UrboxResponse<CartPayVoucherResponse>> getCartPayVoucher(CartPayVoucherRequest request) {
        request.setApp_secret(app_secret);
        request.setApp_id(app_id);

        // Dữ liệu để tạo chữ ký điện tử
        Map<String, Object> dataToSign = new HashMap<>();
        dataToSign.put("app_secret", request.getApp_secret());
        dataToSign.put("app_id", request.getApp_id());

        if (request.getCampaign_code() != null) {
            dataToSign.put("campaign_code", request.getCampaign_code());
        }
        if (request.getSide_user_id() != null) {
            dataToSign.put("site_user_id", request.getSide_user_id());
        }
        if (request.getTtphone() != null) {
            dataToSign.put("ttphone", request.getTtphone());
        }
        if (request.getTtemail() != null) {
            dataToSign.put("ttemail", request.getTtemail());
        }
        if (request.getTtfullname() != null) {
            dataToSign.put("ttfullname", request.getTtfullname());
        }
        if (request.getTransaction_id() != null) {
            dataToSign.put("transaction_id", request.getTransaction_id());
        }
        if (request.getClient_purchase_order() != null) {
            dataToSign.put("client_purchase_order", request.getClient_purchase_order());
        }
        if (request.getShorten() != null) {
            dataToSign.put("shorten", request.getShorten());
        }
        if (request.getIsSendSms() != null) {
            dataToSign.put("isSendSms", request.getIsSendSms());
        }
        if (request.getPin() != null) {
            dataToSign.put("pin", request.getPin());
        }
        if (request.getDataBuy() != null) {
            dataToSign.put("dataBuy", request.getDataBuy());
        }

        // Tạo chữ ký
        String signature = signatureUtil.generateSignature(dataToSign, privateKeyPath);

        // Gọi API
        return callPostApi("/2.0/cart/getCartPayVoucher", request, signature, CartPayVoucherResponse.class);
    }
}
