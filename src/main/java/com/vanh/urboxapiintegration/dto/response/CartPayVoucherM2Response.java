package com.vanh.urboxapiintegration.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CartPayVoucherM2Response {
    private int pay;
    private String transaction_id;
    private String campaign_code;
    private String linkCart;
    private String linkShippingInfo;
    private Cart cart;

    @Data
    public static class Cart {
        private String id;
        private String cartNo;
        private String money_total;
        private String money_ship;
        private List<String> link_gift;
        private List<CodeLinkGift> code_link_gift;
    }

    @Data
    public static class CodeLinkGift {
        private String cart_detail_id;
        private String code;
        private String pin;
        private String serial;
        private String priceId;
        private String price;
        private String code_display;
        private int code_display_type;
        private String link;
        private String token;
        private String code_image;
        private String expired;
        private String estimateDelivery;
        private String ttemail;
        private String ttphone;
        private int city_id;
        private int district_id;
        private int ward_id;
        private String ttaddress;
        private String delivery_note;
    }
}
