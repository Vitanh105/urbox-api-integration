package com.vanh.urboxapiintegration.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CartPayVoucherM1Response {
    private int pay;
    private String transaction_id;
    private String campaign_code;
    private String cartCreated;
    private String linkCart;
    private String linkCombo;
    private String linkShippingInfo;  // Có giá trị
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
        private int price;
        private String code_display;
        private int code_display_type;
        private String code_image;
        private String link;
        private String priceId;
        private String token;
        private String expired;
        private String estimateDelivery;
        private String ttemail;
        private String ttphone;
        private int city_id;
        private int district_id;
        private int ward_id;
        private String delivery_note;
        private String ttaddress;
    }
}
