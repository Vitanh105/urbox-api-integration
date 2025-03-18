package com.vanh.urboxapiintegration.dto.category;

import com.vanh.urboxapiintegration.dto.brand.BrandResponse;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {
    private int done;
    private String msg;
    private String microtime;
    private int status;
    private List<CategoryData> data;

    @Data
    public static class CategoryData {
        private String images;
        private String id; // Mã danh mục
        private String title;
    }
}
