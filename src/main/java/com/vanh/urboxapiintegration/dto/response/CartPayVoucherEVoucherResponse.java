package com.vanh.urboxapiintegration.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CartPayVoucherEVoucherResponse {
    private int pay;
    private String transactionId;
    private String campaign_code;
    private String cart_created;
    private String linkCart;
    private String linkCombo;
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
        private String code_display;
        private int code_display_type;
        private String link;
        private String token;
        private String expired;
        private int valid_time;
        private String gift_id;
        private int expired_time;
        private String estimateDelivery;
        private int card_id;
        private String code_image;
        private String ttemail;
        private String ttphone;
        private String receive_code;
        private String delivery_note;
        private int type;
        private int urcard_id;
        private float price;
        private int city_id;
        private int district_id;
        private int ward_id;
        private String ttaddress;
        private int deliveryCode;
    }
}
