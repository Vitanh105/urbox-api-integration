package com.vanh.urboxapiintegration.controller;

import com.vanh.urboxapiintegration.dto.request.CartPayVoucherEVoucherRequest;
import com.vanh.urboxapiintegration.dto.request.CartPayVoucherM1Request;
import com.vanh.urboxapiintegration.dto.request.CartPayVoucherM2Request;
import com.vanh.urboxapiintegration.dto.response.*;
import com.vanh.urboxapiintegration.service.*;
import jakarta.validation.Valid;
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
    private final CartListService cartListService;
    private final CartTransactionService cartTransactionService;


    public UrboxController(BrandService brandService,
                           CategoryService categoryService,
                           GiftService giftService,
                           GiftDetailService giftDetailService,
                           CartPayVoucherService cartPayVoucherService,
                           CartListService cartListService, CartTransactionService cartTransactionService) {
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.giftService = giftService;
        this.giftDetailService = giftDetailService;
        this.cartPayVoucherService = cartPayVoucherService;
        this.cartListService = cartListService;
        this.cartTransactionService = cartTransactionService;
    }

    //Lấy danh sách thương hiệu
    @GetMapping("/brand")
    public Mono<UrboxResponse<BrandResponse>> getBrand(@RequestParam(required = false) Integer cat_id,
                                                       @RequestParam(required = false) Integer per_page,
                                                       @RequestParam(required = false) Integer page_no) {
        return brandService.getBrand(cat_id, per_page, page_no);
    }

    //Lấy danh sách danh mục
    @GetMapping("/category")
    public Mono<UrboxResponse<CategoryResponse>> getCategory(@RequestParam(required = false) Integer parent_id,
                                                             @RequestParam(required = false) String lang) {
        return categoryService.getCategory(parent_id, lang);
    }

    //Lấy danh sách quà tặng từ kho quà UrBox
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

    //Lấy chi tiết 1 quà tặng
    @GetMapping("/gift/detail")
    public Mono<UrboxResponse<GiftDetailResponse>> getGiftDetail(@RequestParam String id,
                                                                 @RequestParam(required = false) String lang) {
        return giftDetailService.getGiftDetail(id, lang);
    }

    //Tạo yêu cầu đổi quà đến UrBox - Quà eVoucher
    @PostMapping("/cart/cartPayVoucher/eVoucher")
    public Mono<UrboxResponse<CartPayVoucherEVoucherResponse>> cartPayVoucherEVoucher(
            @Valid @RequestBody CartPayVoucherEVoucherRequest request) {
        return cartPayVoucherService.cartPayVoucherEVoucher(request);
    }

    //Tạo yêu cầu đổi quà đến UrBox - Quà vật lý - Cách 1: Đối tác gửi yêu cầu đặt hàng cho UrBox
    @PostMapping("/cart/cartPayVoucher/voucherM1")
    public Mono<UrboxResponse<CartPayVoucherM1Response>> cartPayVoucherPhysicalMethod1(
            @Valid @RequestBody CartPayVoucherM1Request request) {
        return cartPayVoucherService.cartPayVoucherPhysicalMethod1(request);
    }

    //Tạo yêu cầu đổi quà tới UrBox - Quà vật lý - Cách 2: Đối tác gửi thông tin giao nhận cho UrBox
    @PostMapping("/cart/cartPayVoucher/voucherM2")
    public Mono<UrboxResponse<CartPayVoucherM2Response>> cartPayVoucherPhysicalMethod2(
            @Valid @RequestBody CartPayVoucherM2Request request) {
        return cartPayVoucherService.cartPayVoucherPhysicalMethod2(request);
    }

    //Tra cứu theo mã user và thời gian
    @GetMapping("/cart/getlist")
    public Mono<UrboxResponse<CartListResponse>> getCartList(
            @RequestParam String site_user_id,
            @RequestParam(required = false) String campaign_code,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String transaction_id) {
        return cartListService.getCartList(site_user_id, campaign_code, startDate, endDate, transaction_id);
    }

    //Tra cứu theo chi tiết đơn hàng
    @GetMapping("/cart/getByTransaction")
    public Mono<UrboxResponse<CartTransactionResponse>> getCartByTransaction(@RequestParam String transaction_id) {
        return cartTransactionService.getCartByTransaction(transaction_id);
    }
}