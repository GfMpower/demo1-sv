package com.example.demo.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice //标识为全局控制器增强, 统一处理控制器层异常
public class GlobalExceptionHandler {

    /**
     * 处理自定义业务异常
     * 当控制抛出BusinessException, 由此方法处理
     * @param e 业务异常对象
     * @return 返回统一格式的错误结果
     */
    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusinessException(BusinessException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 处理参数验证异常
     * 当控制器参数验证不通过时, 由此方法处理
     * @param e 参数异常对象
     * @return 返回400错误请求
     */
    @ExceptionHandler(IllegalAccessError.class)
    public Result<Void> handleBusinessException(IllegalAccessError e) {
        return Result.badRequest(e.getMessage());
    }

    /**
     * 处理其他未明确捕获的异常
     * 作为异常处理的兜底方法, 确定所有异常都能被统一处理
     * @param e 异常对象
     * @return 返回500错误请求的结果
     */
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        //打印异常堆栈信息, 便于调试和问题排查
        e.printStackTrace();
        return Result.error("系统内部错误");
    }
}
