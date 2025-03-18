package com.vanh.urboxapiintegration.service;

import com.vanh.urboxapiintegration.dto.gift.GiftListRequest;
import com.vanh.urboxapiintegration.dto.gift.GiftListResponse;
import com.vanh.urboxapiintegration.model.GiftList;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GiftListService {
    private final WebClient webClient;

    public GiftListService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<GiftListResponse> getGiftList(GiftListRequest giftListRequest) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        if(giftListRequest.getCat_id() != null) {
            queryParams.add("cat_id", giftListRequest.getCat_id());
        }
        if(giftListRequest.getBrand_id() != null) {
            queryParams.add("brand_id", giftListRequest.getBrand_id());
        }
        queryParams.add("field", String.valueOf(giftListRequest.getField()));
        queryParams.add("lang", String.valueOf(giftListRequest.getLang()));
        queryParams.add("stock", String.valueOf(giftListRequest.getStock()));
        queryParams.add("title", String.valueOf(giftListRequest.getTitle()));
        queryParams.add("per_page", String.valueOf(giftListRequest.getPer_page()));
        queryParams.add("page_no", String.valueOf(giftListRequest.getPage_no()));

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/4.0/gift/lists")
                        .queryParams(queryParams)
                        .build())
                .retrieve()
                .bodyToMono(GiftListResponse.class)
                .map(giftListResponse -> {
                    if (giftListResponse.getStatus() != 200 || giftListResponse.getDone() != 1) {
                        throw new RuntimeException("API error:" + giftListResponse.getMsg());
                    }

                    // Lấy danh sách quà để dùng sau
                    Set<GiftList> giftLists = giftListResponse.getData().getItems().stream().map(item -> {
                        GiftList giftList = new GiftList();
                        giftList.setId(item.getId());
                        giftList.setBrand(item.getBrand());
                        giftList.setBrand_id(item.getBrand_id());
                        giftList.setCat_id(item.getCat_id());
                        giftList.setGift_id(item.getGift_id());
                        giftList.setTitle(item.getTitle());
                        giftList.setType(item.getType());
                        giftList.setPrice(item.getPrice());
                        giftList.setPoint(item.getPoint());
                        giftList.setView(item.getView());
                        giftList.setQuantity(item.getQuantity());
                        giftList.setStock(item.getStock());
                        giftList.setImage(item.getImage());
                        giftList.setImages(item.getImages());
                        giftList.setImages_rectangle(item.getImages_rectangle());
                        giftList.setExpire_duration(item.getExpire_duration());
                        giftList.setCode_display(item.getCode_display());
                        giftList.setCode_display_type(item.getCode_display_type());
                        giftList.setBrandLogoLoyalty(item.getBrandLogoLoyalty());
                        giftList.setBrandImage(item.getBrandImage());
                        giftList.setBrand_name(item.getBrand_name());
                        giftList.setBrand_online(item.getBrand_online());
                        giftList.setParent_cat_id(item.getParent_cat_id());
                        giftList.setUsage_check(item.getUsage_check());
                        giftList.setContent(item.getContent());
                        giftList.setNote(item.getNote());
                        giftList.setCode_quantity(item.getCode_quantity());
                        return giftList;
                    }).collect(Collectors.toSet());
                    return giftListResponse;
                });
    }
}
