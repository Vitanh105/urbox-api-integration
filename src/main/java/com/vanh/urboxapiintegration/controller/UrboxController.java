package com.vanh.urboxapiintegration.controller;

import com.vanh.urboxapiintegration.dto.CategoryResponse;
import com.vanh.urboxapiintegration.dto.GiftResponse;
import com.vanh.urboxapiintegration.dto.UrboxResponse;
import com.vanh.urboxapiintegration.dto.BrandResponse;
import com.vanh.urboxapiintegration.service.BrandService;
import com.vanh.urboxapiintegration.service.CategoryService;
import com.vanh.urboxapiintegration.service.GiftService;
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
    private final GiftService giftService;


    public UrboxController(BrandService brandService, CategoryService categoryService, GiftService giftService) {
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.giftService = giftService;
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

    @GetMapping("/giftlist")
    public Mono<UrboxResponse<GiftResponse>> getGiftList(@RequestParam(required = false) Integer cat_id,
                                                         @RequestParam(required = false) Integer brand_id,
                                                         @RequestParam(required = false) String field,
                                                         @RequestParam(required = false) String lang,
                                                         @RequestParam(required = false) Integer stock,
                                                         @RequestParam(required = false) String title,
                                                         @RequestParam(required = false) Integer per_page,
                                                         @RequestParam(required = false) Integer page_no) {
        return giftService.getGiftList(cat_id, brand_id, field, lang, stock, title, per_page, page_no);
    }
}