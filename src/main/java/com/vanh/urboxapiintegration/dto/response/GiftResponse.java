package com.vanh.urboxapiintegration.dto.response;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class GiftResponse {
    private List<Item> items;
    private int totalPage;
    private String totalResult;

    @Data
    public static class Item {
        private String id;
        private String brand;
        private String brand_id;
        private String cat_id;
        private String cat_title;
        private String gift_id;
        private String title;
        private String type;
        private String price;
        private String point;
        private String view;
        private String quantity;
        private int stock;
        private String image;
        private Map<String, String> images;
        private List<String> images_rectangle;
        private String expire_duration;
        private String code_display;
        private int code_display_type;
        private List<Office> office;
        private String brandLogoLoyalty;
        private String brandImage;
        private String brand_name;
        private String brand_online;
        private String parent_cat_id;
        private int usage_check;
        private String content;
        private String note;
        private String code_quantity;
    }

    @Data
    public static class Office {
        private String id;
        private String store_id;
        private String brand_id;
        private String city_id;
        private String district_id;
        private String ward_id;
        private String street_id;
        private String code;
        private String address;
        private String address_en;
        private String address2;
        private String title_city;
        private String title_district;
        private String title_ward;
        private String title_street;
        private String number;
        private String image;
        private String phone;
        private String sort;
        private String latitude;
        private String longitude;
        private String geo;
        private String isApply;
        private String created;
        private String brand_img_src;
        private String brand_title;
    }
}
