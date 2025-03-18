package com.vanh.urboxapiintegration.dto.brand;

import lombok.Data;

import java.util.List;

@Data
public class BrandResponse {
    private int done;
    private String msg;
    private String microtime;
    private int status;
    private BrandData data;

    @Data
    public static class BrandData {
        private List<BrandItem> items;
        private String textTitle;
        private String brandCount;
        private int totalPage;
    }

    @Data
    public static class BrandItem {
        private String banner;
        private String description;
        private String images;
        private String id;
        private String cat_id;
        private String title;
        private int gift_count;
        private String cat_title;
        private String parent_cat_id;
    }
}
