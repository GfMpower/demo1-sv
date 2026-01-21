package com.example.demo.utils;
/**
 * 响应码枚举
 */
public enum ResultCode {
    // 成功
    SUCCESS(200, "操作成功"),

    // 通用错误
    ERROR(500, "操作失败"),
    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),

    // 用户相关错误
    USER_NOT_EXIST(1001, "用户不存在"),
    USER_ALREADY_EXIST(1002, "用户已存在"),
    USERNAME_OR_PASSWORD_ERROR(1003, "用户名或密码错误"),
    USER_DISABLED(1004, "用户已被禁用"),

    // 商品相关错误
    PRODUCT_NOT_EXIST(2001, "商品不存在"),
    PRODUCT_STOCK_NOT_ENOUGH(2002, "商品库存不足"),

    // 分类相关错误
    CATEGORY_NOT_EXIST(3001, "分类不存在"),

    // 订单相关错误
    ORDER_NOT_EXIST(4001, "订单不存在");

    private final Integer code;
    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}