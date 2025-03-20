package com.vanh.urboxapiintegration.controller;

import com.vanh.urboxapiintegration.dto.CategoryResponse;
import com.vanh.urboxapiintegration.dto.UrboxResponse;
import com.vanh.urboxapiintegration.dto.BrandResponse;
import com.vanh.urboxapiintegration.service.BrandService;
import com.vanh.urboxapiintegration.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/urbox")
public class UrboxController {

    private final BrandService brandService;
    private final CategoryService categoryService;


    public UrboxController(BrandService brandService, CategoryService categoryService) {
        this.brandService = brandService;
        this.categoryService = categoryService;
    }

    @GetMapping("/brand")
    public Mono<UrboxResponse<BrandResponse>> getBrand(@RequestParam(required = false) Integer cat_id,
                                                       @RequestParam(required = false) Integer per_page,
                                                       @RequestParam(required = false) Integer page_no) {
        return brandService.getBrand(cat_id, per_page, page_no);
    }

    @GetMapping("/category")
    public Mono<UrboxResponse<CategoryResponse>> getCategory(@RequestParam(required = false) Integer parent_id,
                                                          @RequestParam(required = false) String lang) {
        return categoryService.getCategory(parent_id, lang);
    }
}