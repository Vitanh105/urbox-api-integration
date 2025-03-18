package com.vanh.urboxapiintegration.controller;

import com.vanh.urboxapiintegration.dto.brand.BrandRequest;
import com.vanh.urboxapiintegration.dto.brand.BrandResponse;
import com.vanh.urboxapiintegration.model.ParentCategory;
import com.vanh.urboxapiintegration.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    //Lấy danh sách parent_category_id(mã danh mục cha)
    @GetMapping("/parent_category")
    public Mono<ResponseEntity<List<ParentCategory>>> getParentCategory(
            @RequestParam(defaultValue = "1") int pageNo) {
        return brandService.getParentCategory(pageNo)
                .map(categories -> ResponseEntity.ok(categories))
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorResume(e -> Mono.just(ResponseEntity.status(500).body(null)));
    }

    //Lấy danh sách thương hiệu
    @GetMapping("/brand")
    public Mono<ResponseEntity<BrandResponse>> getBrand(
            @RequestParam(required = false) String catId,
            @RequestParam(defaultValue = "1") int pageNo) {
        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setCat_id(catId);
        brandRequest.setPer_page(10); // Cấu hình từ application.properties
        brandRequest.setPage_no(pageNo);

        return brandService.getBrands(brandRequest)
                .map(response -> ResponseEntity.ok(response))
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorResume(e -> Mono.just(ResponseEntity.status(500).body(null)));
    }
}
