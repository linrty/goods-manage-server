package com.linrty.goods.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.linrty.goods.bean.User;

/**
 * @author Linrty
 */

public interface UserService extends IService<User> {
    /**
     * 注册
     *
     * @param user 注册信息
     * @return 是否成功
     */
    Boolean register(User user);

    /**
     * 登录
     *
     * @param user 登录信息
     * @return 令牌
     */
    String login(User user);

    /**
     * 更改密码
     *
     * @param user 用户信息
     * @return 是否成功
     */
    Boolean changePassword(User user);
}
