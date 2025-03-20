package com.vanh.urboxapiintegration.controller;

import com.vanh.urboxapiintegration.dto.request.CartPayVoucherRequest;
import com.vanh.urboxapiintegration.dto.response.*;
import com.vanh.urboxapiintegration.service.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/urbox")
public class UrboxController {

    private final BrandService brandService;
    private final CategoryService categoryService;
    private final GiftService giftService;
    private final GiftDetailService giftDetailService;
    private final CartPayVoucherService cartPayVoucherService;


    public UrboxController(BrandService brandService, CategoryService categoryService, GiftService giftService, GiftDetailService giftDetailService, CartPayVoucherService cartPayVoucherService) {
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.giftService = giftService;
        this.giftDetailService = giftDetailService;
        this.cartPayVoucherService = cartPayVoucherService;
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

    @GetMapping("/gift-list")
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

    @GetMapping("/gift/detail")
    public Mono<UrboxResponse<GiftDetailResponse>> getGiftDetail(@RequestParam String id,
                                                                 @RequestParam(required = false) String lang) {
        return giftDetailService.getGiftDetail(id, lang);
    }

    @PostMapping("/cart/pay-voucher")
    public Mono<UrboxResponse<CartPayVoucherResponse>> getCartPayVoucher(@RequestBody CartPayVoucherRequest request) {
        return cartPayVoucherService.getCartPayVoucher(request);
    }
}