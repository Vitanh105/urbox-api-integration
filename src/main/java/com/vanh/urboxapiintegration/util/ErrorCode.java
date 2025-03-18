package com.vanh.urboxapiintegration.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    SUCCESS("200", "Thành công"),
    INVALID_DATA("210", "Không tìm app_id hoặc app_secret"),
    INVALID_ACCESS_URBOX("211", "Access Urbox không đúng."),
    INVALID_AUTHENTICATION("212", "Thông tin xác thực không chính xác."),
    AUTHENTICATION_ERROR("213", "Xảy ra lỗi xác thực."),
    OUT_OF_STOCK("220", "Hiện kho quà đang hết vui lòng quay lại sau"),
    PURCHASED_PRODUCT_NOT_FOUND("221", "Không tìm thấy sản phẩm mua."),
    PRODUCT_NOT_FOUND("222", "Không tìm thấy sản phẩm"),
    GIFT_EXPIRED("223", "1 trong số quà tặng bạn đặt mua đã hết hạn, bạn hãy chọn lại quà khác"),
    GIFT_NOT_FOUND("224", "Không tìm thấy quà tặng"),
    PRODUCT_OUT_OF_STOCK("225", "Sản phẩm xxx đang hết, vui lòng bỏ sản phẩm ra khỏi giỏ hàng."),
    GIFT_NOT_INCLUDED("226", "Quà tặng không nằm trong chương trình."),
    INVALID_EMAIL("304", "Email không đúng định dạng"),
    MONEY_NOT_ENOUGH("306", "Hệ thống khách hàng không đủ tiền"),
    CAMPAIGN_CODE_MISSING("307", "Request bị thiếu campaign_code"),
    INVALID_CAMPAIGN_CODE("308", "Mã chương trình không thuộc app_id"),
    TRANSACTION_NOT_FOUND("403", "Không tìm thấy Mã Giao Dịch (transaction_id)"),
    INVALID_QUANTITY("404", "Số lượng phải lớn hơn 0"),
    INVALID_PROCESS("405", "Bạn vui lòng chọn quà muốn tặng trước."),
    VOUCHER_CODE_NOT_TRUE("406", "Mã khuyến mại không đúng"),
    PRODUCT_NOT_ENOUGH("407", "Số lượng sản phẩm không đủ"),
    ORDER_ERROR("408", "Hệ thống hiện tại không tạo được đơn hàng."),
    DATA_NOT_FOUND("409", "Không tìm thấy dữ liệu."),
    ADDRESS_NOT_FOUND("601", "Không tìm thấy địa chỉ"),
    CITY_ID_NOT_FOUND("602","Không tìm thấy mã tỉnh thành phố"),
    CITY_NOT_EXIST("603","Tỉnh thành bạn nhập không tồn tại"),
    DISTRICT_ID_NOT_FOUND("604","Không tìm thấy mã quận huyện"),
    DISTRICT_NOT_EXIST("605","Quận huyện bạn nhập không tồn tại"),
    DISTRICT_NOT_BELONGED("606","Quận huyện không thuộc thành phố bạn chọn"),
    WARD_ID_NOT_FOUND("607","Không tìm thấy mã phường xã"),
    WARD_NOT_EXIST("608","Phường xã bạn nhập không tồn tại"),
    WARD_NOT_BELONG("609","Xã phường không thuộc tỉnh thành bạn chọn"),
    PHONE_NUMBER_NOT_FOUND("610","Không tìm thấy số điện thoại"),
    ;

    private final String code;
    private final String message;
}
