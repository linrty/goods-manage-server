package com.linrty.goods.common;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 网络请求响应统一包装类
 * @author Linrty
 * @date 2022/3/24 14:45
 * @version 1.0
 */

@Data
@Builder(toBuilder=true)
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    /**
     * http返回码
     */
    @JSONField(name = "code")
    private Integer code;
    /**
     * 消息
     */
    @JSONField(name = "msg")
    private String msg;
    /**
     * 返回的具体数据
     */
    @JSONField(name = "data")
    private T data;



    public static <T> CommonResult<T> success(T t) {
        return new CommonResult<T>(200, "操作成功", t);
    }

    public static <T> CommonResult<T> error(T t) {
        return new CommonResult<T>(300, "操作失败", t);
    }

}
