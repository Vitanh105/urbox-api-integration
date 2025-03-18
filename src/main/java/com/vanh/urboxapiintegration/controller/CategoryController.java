package com.vanh.urboxapiintegration.controller;

import com.vanh.urboxapiintegration.dto.category.CategoryRequest;
import com.vanh.urboxapiintegration.dto.category.CategoryResponse;
import com.vanh.urboxapiintegration.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/brand")
    public Mono<ResponseEntity<CategoryResponse>> getCategory(
            @RequestParam(required = false) String parent_id) {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setParent_id(parent_id);
        categoryRequest.setLang("vi"); // Cấu hình từ application.properties

        return categoryService.getCategory(categoryRequest)
                .map(response -> ResponseEntity.ok(response))
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorResume(e -> Mono.just(ResponseEntity.status(500).body(null)));
    }
}
