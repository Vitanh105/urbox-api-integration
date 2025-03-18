package com.vanh.urboxapiintegration.dto.brand;

import lombok.Data;

@Data
public class BrandRequest {
    private String app_secret;
    private int app_id;
    private String cat_id; // Mã danh mục cha
    private int per_page;
    private int page_no;
}
