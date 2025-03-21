package com.vanh.urboxapiintegration.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CartTransactionResponse {
    private String id;
    private String campaign_code;
    private String linkCart;
    private String money_ship;
    private String money_total;
    private String created;
    private String created_timestamp;
    private String pay_time;
    private String pay_status;
    private int pay_status_code;
    private Object customer;
    private Receiver receiver;
    private List<TransactionDetail> detail;
    private int item_quantity;
    private String linkCombo;

    @Data
    public static class Receiver {
        private String address;
        private String phone;
        private String email;
    }

    @Data
    public static class TransactionDetail {
        private String link;
        private String gift_id;
        private String priceId;
        private String using_time;
        private String delivery;
        private int deliveryCode;
        private String estimateDelivery;
        private String delivery_note;
        private List<Object> topup;
        private String code;
        private String expired;
        private String code_display;
        private int code_display_type;
        private String finish_time;
        private String delivery_tracking;
        private String created_timestamp;
        private String id;
    }
}
