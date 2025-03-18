package com.vanh.urboxapiintegration.service;

import com.vanh.urboxapiintegration.dto.category.CategoryRequest;
import com.vanh.urboxapiintegration.dto.category.CategoryResponse;
import com.vanh.urboxapiintegration.model.Category;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final WebClient webClient;

    @Value("${api.lang}")
    private String lang;

    public CategoryService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<CategoryResponse> getCategory(CategoryRequest categoryRequest) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("lang", String.valueOf(categoryRequest.getLang()));
        if (categoryRequest.getParent_id() != null) {
            queryParams.add("parent_id", categoryRequest.getParent_id());
        }

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/2.0/category/catbyparent")
                        .queryParams(queryParams)
                        .build())
                .retrieve()
                .bodyToMono(CategoryResponse.class)
                .map(categoryResponse -> {
                    if (categoryResponse.getStatus() != 200 || categoryResponse.getDone() != 1) {
                        throw new RuntimeException("API error:" + categoryResponse.getMsg());
                    }
                    //Lấy ra danh sách danh mục, có thể cần dùng sau
                    Set<Category> categories = categoryResponse.getData().stream().map(data -> {
                        Category category = new Category();
                        category.setImages(data.getImages());
                        category.setId(data.getId());
                        category.setTitle(data.getTitle());
                        return category;
                    }).collect(Collectors.toSet());
                    return categoryResponse;
                });
    }
}
