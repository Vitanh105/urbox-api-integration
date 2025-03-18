package com.vanh.urboxapiintegration.dto.category;

import lombok.Data;

@Data
public class CategoryRequest {
    private String app_secret;
    private int app_id;
    private String parent_id; // Mã danh mục cha
    private String lang;
}
