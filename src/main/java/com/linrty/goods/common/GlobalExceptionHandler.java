package com.linrty.goods.common;

import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @PackageName: com.linrty.ctransaction.common
 * @ClassName: GlobalExceptionHandler
 * @Description: 全局异常拦截类，拦截到异常后直接返回响应失败，并放回响应失败对应的消息
 * @author: Linrty
 * @date: 2022/3/24 15:33
 */
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public CommonResult<String> exceptionHandler(Exception e) {
        return CommonResult.error(e.getMessage());
    }
}
