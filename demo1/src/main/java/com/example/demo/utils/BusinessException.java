package com.example.demo.utils;

public class BusinessException extends RuntimeException{
    //显示声明与劣化版本UID, 确保类兼容性
    private static final long serialVersionUID = 1L;
    //显示异常错误码
    private Integer code;
    //构造方法 -仅包含错误信息
    public BusinessException(String message) {
        super(message);
        this.code = 500;
    }
    //构造方法 -包含错误信息和错误码
    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }
    //构造方法 -基于预定义的枚举
    public BusinessException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
    }
    //获取异常错误码
    public Integer getCode() {
        return code;
    }
}
