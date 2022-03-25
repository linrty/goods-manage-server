package com.linrty.goods.common;

/**
 * @PackageName: com.linrty.goods.common
 * @ClassName: ContextHolder
 * @Description: 一个请求线程的上下文
 * @author: Linrty
 * @date: 2022/3/24 15:47
 */
public class ContextHolder {
    public static ThreadLocal<Long> context = new ThreadLocal<>();

    /**
     * 将userId保存在线程中，从token解析后直接保存在线程中，
     * 这样就不需要再向前端要求携带用户id了
     * @param userId
     */
    public static void setUserId(Long userId) {
        context.set(userId);
    }

    public static Long getUserId() {
        return context.get();
    }

    public static void shutdown() {
        context.remove();
    }
}
