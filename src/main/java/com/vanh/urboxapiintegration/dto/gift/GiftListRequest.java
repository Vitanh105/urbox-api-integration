package com.vanh.urboxapiintegration.dto.gift;

import lombok.Data;

@Data
public class GiftListRequest {
    private String app_secret;
    private int app_id;
    private String cat_id; // Mã danh mục
    private String brand_id; // Mã thương hiệu
    private String field;
    private String lang;
    private int stock;
    private String title; // Tên quà
    private int per_page;
    private int page_no;
}
