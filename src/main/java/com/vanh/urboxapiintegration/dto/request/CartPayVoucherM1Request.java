package com.vanh.urboxapiintegration.dto.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CartPayVoucherM1Request {
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

    private String lang;
    private int shorten;

    @NotNull
    private Integer isSendSms;

    @NotNull
    private Integer shipping_info_available;  // Bắt buộc, phải là 1

    private List<DataBuy> dataBuy;

    @Data
    public static class DataBuy {
        @NotNull
        private String priceId;

        @NotNull
        private Integer quantity;

        private String amount;
    }

    @AssertTrue(message = "shipping_info_available phải là 1 cho Cách 1")
    private boolean isShippingInfoAvailableValid() {
        return shipping_info_available != null && shipping_info_available == 1;
    }
}
