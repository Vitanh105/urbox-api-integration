package com.vanh.urboxapiintegration.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CartPayVoucherRequest {
    @NotNull
    private String app_secret;

    @NotNull
    private int app_id;

    private String campaign_code;
    private String side_user_id;
    private String ttphone;
    private String ttemail;
    private String ttfullname;
    private String transaction_id;
    private String client_purchase_order;
    private Integer shorten;
    private Integer isSendSms;
    private String pin;
    private List<DataBuy> dataBuy;

    @Data
    public static class DataBuy {
        private String priceId;
        private String quantity;
        private String amount;
    }
}
