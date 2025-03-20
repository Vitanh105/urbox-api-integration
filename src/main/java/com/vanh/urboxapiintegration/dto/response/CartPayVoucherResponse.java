package com.vanh.urboxapiintegration.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CartPayVoucherResponse {
    private int pay;
    private String transaction_id;
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
        private String code_display;
        private int code_display_type;
        private String link;
        private String code;
        private int card_id;
        private String pin;
        private String serial;
        private String priceId;
        private String gift_id;
        private String token;
        private String expired;
        private long expired_time;
        private String code_image;
        private String estimateDelivery;
        private String ttemail;
        private String ttphone;
        private String receive_code;
        private int city_id;
        private int district_id;
        private int ward_id;
        private String delivery_note;
        private String ttaddress;
        private int deliveryCode;
        private int type;
        private int urcard_id;
        private long price;
    }
}
