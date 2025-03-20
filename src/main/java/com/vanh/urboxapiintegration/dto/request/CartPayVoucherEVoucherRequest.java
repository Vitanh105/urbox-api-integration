package com.vanh.urboxapiintegration.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CartPayVoucherEVoucherRequest {
    @NotNull
    private String app_secret;

    @NotNull
    private int app_id;

    @NotNull
    private String campaign_code;

    @NotNull
    private String site_user_id;

    @NotNull
    private String ttphone;

    private String ttemail;
    private String ttfullname;

    @NotNull
    private String transaction_id;

    private String client_purchase_order;
    private int shorten;

    @NotNull
    private Integer isSendSms;

    private String pin;
    private List<DataBuy> dataBuy;

    @Data
    public static class DataBuy {
        @NotNull
        private String priceId;

        @NotNull
        private String quantity;

        private String amount;
    }
}
