package com.vanh.urboxapiintegration.service;

import com.vanh.urboxapiintegration.dto.request.CartPayVoucherEVoucherRequest;
import com.vanh.urboxapiintegration.dto.request.CartPayVoucherM1Request;
import com.vanh.urboxapiintegration.dto.request.CartPayVoucherM2Request;
import com.vanh.urboxapiintegration.dto.response.CartPayVoucherEVoucherResponse;
import com.vanh.urboxapiintegration.dto.response.CartPayVoucherM1Response;
import com.vanh.urboxapiintegration.dto.response.CartPayVoucherM2Response;
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

    @Value("${app.private-key}")
    private String privateKeyPath;

    public CartPayVoucherService(WebClient webClient, SignatureUtil signatureUtil) {
        super(webClient);
        this.signatureUtil = signatureUtil;
    }

    // Quà eVoucher
    public Mono<UrboxResponse<CartPayVoucherEVoucherResponse>> cartPayVoucherEVoucher(CartPayVoucherEVoucherRequest request) {
        request.setApp_secret(app_secret);
        request.setApp_id(app_id);

        // Tạo dữ liệu để ký
        Map<String, Object> dataToSign = new HashMap<>();
        dataToSign.put("app_secret", request.getApp_secret());
        dataToSign.put("app_id", request.getApp_id());
        dataToSign.put("campaign_code", request.getCampaign_code());
        dataToSign.put("dataBuy", request.getDataBuy());
        dataToSign.put("isSendSms", request.getIsSendSms());
        dataToSign.put("site_user_id", request.getSite_user_id());
        dataToSign.put("transaction_id", request.getTransaction_id());

        // Tạo chữ ký
        String signature = signatureUtil.generateSignature(dataToSign, privateKeyPath);

        return callPostApi("/2.0/cart/cartPayVoucher", request, signature, CartPayVoucherEVoucherResponse.class);
    }

    // Quà vật lý - Cách 1
    public Mono<UrboxResponse<CartPayVoucherM1Response>> cartPayVoucherPhysicalMethod1(CartPayVoucherM1Request request) {
        request.setApp_secret(app_secret);
        request.setApp_id(app_id);

        Map<String, Object> dataToSign = new HashMap<>();
        dataToSign.put("app_secret", request.getApp_secret());
        dataToSign.put("app_id", request.getApp_id());
        dataToSign.put("campaign_code", request.getCampaign_code());
        dataToSign.put("dataBuy", request.getDataBuy());
        dataToSign.put("isSendSms", request.getIsSendSms());
        dataToSign.put("site_user_id", request.getSite_user_id());
        dataToSign.put("transaction_id", request.getTransaction_id());

        String signature = signatureUtil.generateSignature(dataToSign, privateKeyPath);

        return callPostApi("/2.0/cart/cartPayVoucher", request, signature, CartPayVoucherM1Response.class);
    }

    // Quà vật lý - Cách 2
    public Mono<UrboxResponse<CartPayVoucherM2Response>> cartPayVoucherPhysicalMethod2(CartPayVoucherM2Request request) {
        request.setApp_secret(app_secret);
        request.setApp_id(app_id);

        Map<String, Object> dataToSign = new HashMap<>();
        dataToSign.put("app_secret", request.getApp_secret());
        dataToSign.put("app_id", request.getApp_id());
        dataToSign.put("campaign_code", request.getCampaign_code());
        dataToSign.put("dataBuy", request.getDataBuy());
        dataToSign.put("isSendSms", request.getIsSendSms());
        dataToSign.put("site_user_id", request.getSite_user_id());
        dataToSign.put("transaction_id", request.getTransaction_id());

        String signature = signatureUtil.generateSignature(dataToSign, privateKeyPath);

        return callPostApi("/2.0/cart/cartPayVoucher", request, signature, CartPayVoucherM2Response.class);
    }
}
