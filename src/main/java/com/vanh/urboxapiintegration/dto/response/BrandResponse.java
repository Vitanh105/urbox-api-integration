package com.vanh.urboxapiintegration.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class BrandResponse {
    private List<Item> items;
    private String textTitle;
    private String brand_count;
    private int totalPage;

    @Data
    public static class Item {
        private String banner;
        private String description;
        private String id;
        private String title;
        private String cat_id;
        private String cat_title;
        private int gift_count;
        private String parent_cat_id;
        private String images;
    }
}