package com.vanh.urboxapiintegration.controller;

import com.vanh.urboxapiintegration.dto.UrboxResponse;
import com.vanh.urboxapiintegration.dto.BrandResponse;
import com.vanh.urboxapiintegration.service.BrandService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class BrandController {

    private final BrandService brandService;


    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/brand")
    public Mono<UrboxResponse<BrandResponse>> getBrand(@RequestParam(required = false) String cat_id,
                                                       @RequestParam(required = false) Integer per_page,
                                                       @RequestParam(required = false) Integer page_no) {
        return brandService.getBrand(cat_id, per_page, page_no);
    }
}