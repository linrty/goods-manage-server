package com.linrty.goods.common;
import com.linrty.goods.util.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @PackageName: com.linrty.ctransaction.common
 * @ClassName: TokenInterceptor
 * @Description: Token拦截器，除了某些请求可以不需要在header内携带token，其他都需要携带，在Controller层处理之前就进行拦截验证token
 * @author: Linrty
 * @date: 2022/3/24 15:50
 */
public class TokenInterceptor implements HandlerInterceptor {

    /**
     * 请求头
     */
    private static final String HEADER_AUTH = "Authorization";

    /**
     * 安全的url，不需要令牌
     */
    private static final List<String> SAFE_URL_LIST = Arrays.asList("/userInfo/login", "/userInfo/register");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        response.setContentType("application/json; charset=utf-8");

        String url = request.getRequestURI().substring(request.getContextPath().length());
        System.out.println(url);
        // 登录和注册等请求不需要令牌
        if (SAFE_URL_LIST.contains(url)) {
            return true;
        }

        // 从请求头里面读取token
        String token = request.getHeader(HEADER_AUTH);
        if (token == null) {
            throw new RuntimeException("请求失败，令牌为空");
        }

        // 解析令牌，除了userId还可以存取其他用户信息在token内
        Map<String, Object> map = JwtUtil.resolveToken(token);
        Long userId = Long.parseLong(map.get("userId").toString());
        ContextHolder.setUserId(userId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ContextHolder.shutdown();
    }
}