package com.vanh.urboxapiintegration.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CartListResponse {
    private List<CartItem> data;
    private int totalPage;

    @Data
    public static class CartItem {
        private String id;
        private String linkCart;
        private String transaction_id;
        private String campaign_code;
        private String created;
        private String pay_time;
        private String pay_status;
        private int pay_status_code;
        private List<CartDetail> detail;
        private String linkCombo;
        private String createdTimestamp;
        private int item_quantity;
    }

    @Data
    public static class CartDetail {
        private String id;
        private String link;
        private String gift_id;
        private String gift_detail_id;
        private String gift_title;
        private String gift_detail_title;
        private String usage_status;
        private String code_image;
        private String image;
        private String using_time;
        private String brandImage;
        private String price;
        private String delivery;
        private int deliveryCode;
        private String code;
        private String brandTitle;
        private String expired;
        private String code_display;
        private int code_display_type;
        private int item_quantity;
        private String type;
        private int usage_status_code;
        private String delivery_required;
        private List<Object> topup;
        private String images_rectangle;
    }
}
