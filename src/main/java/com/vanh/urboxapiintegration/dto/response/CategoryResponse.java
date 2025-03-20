package com.vanh.urboxapiintegration.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {
    private List<Item> data;

    @Data
    public static class Item {
        private String images;
        private String id;
        private String title;
    }
}