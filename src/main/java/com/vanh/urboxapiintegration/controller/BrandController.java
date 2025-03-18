package com.vanh.urboxapiintegration.controller;

import com.vanh.urboxapiintegration.dto.brand.BrandResponse;
import com.vanh.urboxapiintegration.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public Mono<ResponseEntity<BrandResponse>> getBrands(
            @RequestParam(required = false) String catId,
            @RequestParam(required = false) Integer perPage,
            @RequestParam(required = false) Integer pageNo) {
        return brandService.getBrands(catId, perPage, pageNo)
                .map(response -> {
                    if (response.getDone() == 1 && response.getStatus() == 200 && catId != null && response.getData() != null) {
                        response.getData().setItems(response.getData().getItems().stream()
                                .filter(item -> catId.equals(item.getParent_cat_id()))
                                .toList());
                    }
                    if (response.getDone() == 1 && response.getStatus() == 200) {
                        return ResponseEntity.ok(response);
                    } else {
                        return ResponseEntity.status(response.getStatus()).body(response);
                    }
                })
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorResume(e -> Mono.just(ResponseEntity.status(500).body(
                        new BrandResponse().setStatus(500).setMsg("Error: " + e.getMessage()))));
    }

    @GetMapping("/parent-cat-id")
    public ResponseEntity<Set<String>> getCachedParentCatIds() {
        Set<String> parentCatIds = brandService.getCachedParentCatIds();
        return ResponseEntity.ok(parentCatIds != null ? parentCatIds : new HashSet<>());
    }
}