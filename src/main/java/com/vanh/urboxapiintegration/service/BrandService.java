package com.vanh.urboxapiintegration.service;

import com.vanh.urboxapiintegration.dto.brand.BrandRequest;
import com.vanh.urboxapiintegration.dto.brand.BrandResponse;
import com.vanh.urboxapiintegration.model.Brand;
import com.vanh.urboxapiintegration.model.ParentCategory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BrandService {
    private final WebClient webClient;

    @Value("${api.per-page}")
    private int per_page;

    public BrandService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<BrandResponse> getBrands(BrandRequest brandRequest) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("per_page", String.valueOf(brandRequest.getPer_page()));
        queryParams.add("page_no", String.valueOf(brandRequest.getPage_no()));
        if (brandRequest.getCat_id() != null) {
            queryParams.add("cat_id", brandRequest.getCat_id());
        }

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/4.0/gift/brand")
                        .queryParams(queryParams)
                        .build())
                .retrieve()
                .bodyToMono(BrandResponse.class)
                .map(brandResponse -> {
                    if (brandResponse.getStatus() != 200 || brandResponse.getDone() != 1) {
                        throw new RuntimeException("API error:" + brandResponse.getMsg());
                    }
                    //Lấy ra danh sách thương hiệu, có thể cần dùng sau
                    Set<Brand> brands = brandResponse.getData().getItems().stream().map(item -> {
                        Brand brand = new Brand();
                        brand.setBanner(item.getBanner());
                        brand.setDescription(item.getDescription());
                        brand.setImages(item.getImages());
                        brand.setId(item.getId());
                        brand.setCat_id(item.getCat_id());
                        brand.setTitle(item.getTitle());
                        brand.setGift_count(item.getGift_count());
                        brand.setCat_title(item.getCat_title());
                        brand.setParent_cat_id(item.getParent_cat_id());
                        return brand;
                    }).collect(Collectors.toSet());
                    return brandResponse;
                });
    }

    //Lấy danh sách mã danh mục cha
    public Mono<List<ParentCategory>> getParentCategory(int page_no) {
        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setPer_page(per_page);
        brandRequest.setPage_no(page_no);

        return getBrands(brandRequest)
                .map(response -> {
                    // Thu thập danh sách parent_cat_id
                    Set<ParentCategory> categories = response.getData().getItems().stream()
                            .map(item -> {
                                ParentCategory parentCategory = new ParentCategory();
                                parentCategory.setParent_cat_id(item.getParent_cat_id());
                                return parentCategory;
                            })
                            .collect(Collectors.toSet());
                    return List.copyOf(categories);
                });
    }
}
