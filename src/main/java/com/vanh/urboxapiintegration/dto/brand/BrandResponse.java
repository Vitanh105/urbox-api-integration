package com.vanh.urboxapiintegration.dto.brand;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Data
public class BrandResponse {
    private int done;
    private String msg;
    private String microtime;
    private int status;
    private Data data;

    @lombok.Data
    public static class Data {
        private List<Item> items;
        private String brand_count;
        private String textTitle;
        private int totalPage;
    }

    @lombok.Data
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
