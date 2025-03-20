package com.vanh.urboxapiintegration.service;

import com.vanh.urboxapiintegration.dto.response.GiftResponse;
import com.vanh.urboxapiintegration.dto.response.UrboxResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class GiftService extends UrboxService {
    public GiftService(WebClient webClient) {
        super(webClient);
    }

    public Mono<UrboxResponse<GiftResponse>> getGiftList(Integer cat_id,
                                                         Integer brand_id,
                                                         String field,
                                                         String lang,
                                                         Integer stock,
                                                         String title,
                                                         Integer per_page,
                                                         Integer page_no) {
        Map<String, Object> params = new HashMap<>();
        if (cat_id != null) params.put("cat_id", cat_id);
        if (brand_id != null) params.put("brand_id", brand_id);
        if (field != null) params.put("field", field);
        if (lang != null) params.put("lang", lang);
        if (stock != null) params.put("stock", stock);
        if (title != null) params.put("title", title);
        if (per_page != null) params.put("per_page", per_page);
        if (page_no != null) params.put("page_no", page_no);
        return callGetApi("/4.0/gift/lists", params, GiftResponse.class);
    }
}