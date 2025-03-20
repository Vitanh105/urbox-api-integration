package com.vanh.urboxapiintegration.dto.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CartPayVoucherM2Request {
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

    @NotNull
    private String ttfullname;

    @NotNull
    private String transaction_id;

    private int shorten;

    @NotNull
    private Integer isSendSms;

    @NotNull
    private Integer shipping_info_available;  // Bắt buộc, phải là 2

    private List<DataBuy> dataBuy;

    @NotNull
    private Integer city_id;

    @NotNull
    private Integer district_id;

    @NotNull
    private Integer ward_id;

    @NotNull
    private String ttaddress;

    private String delivery_note;

    @Data
    public static class DataBuy {
        @NotNull
        private String priceId;

        @NotNull
        private String quantity;

        private String amount;
    }

    @AssertTrue(message = "shipping_info_available phải là 2 cho Cách 2")
    private boolean isShippingInfoAvailableValid() {
        return shipping_info_available != null && shipping_info_available == 2;
    }
}
