package com.vanh.urboxapiintegration.dto.response;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class GiftDetailResponse {
    private String id;
    private String brand;
    private String brand_id;
    private String code_display;
    private int code_display_type;
    private String cat_id;
    private String gift_id;
    private String title;
    private String type;
    private String price;
    private String point;
    private String valuex;
    private String weight;
    private String justGetOrder;
    private String view;
    private String quantity;
    private int usage_check;
    private String image;
    private Map<String, String> images;
    private List<String> images_rectangle;
    private String expire_duration;
    private String parent_cat_id;
    private String brand_online;
    private String brandImage;
    private String content;
    private String note;
    private List<GiftResponse.Office> office;
}
