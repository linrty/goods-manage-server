package com.linrty.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linrty.goods.bean.User;
import com.linrty.goods.common.ContextHolder;
import com.linrty.goods.dao.UserDAO;
import com.linrty.goods.service.UserService;
import com.linrty.goods.util.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @PackageName: com.linrty.ctransaction.service.impl
 * @ClassName: UserServiceImpl
 * @Description: User相关操作的Service层实现类
 * @author: Linrty
 * @date: 2022/3/24 15:39
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDAO, User> implements UserService {

    @Override
    public Boolean register(User user) {
        List<User> selectedList = list(new LambdaQueryWrapper<User>().eq(User::getPhone, user.getUserName()));
        if (!selectedList.isEmpty()) {
            throw new RuntimeException("注册失败，该手机号已经注册过");
        }
        // 密码加密，该密码加密方式自带了盐
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return save(user);
    }

    @Override
    public String login(User user) {
        List<User> selectedList = list(new LambdaQueryWrapper<User>()
                .eq(User::getPhone, user.getPhone()));
        if (selectedList.isEmpty()) {
            throw new RuntimeException("登录失败，账号不存在");
        }
        User selected = selectedList.get(0);
        String encodedPassword = selected.getPassword();
        // 判断密码是否正确，先进行解密再进行对比因为数据库内的密码和前端传过来的密码都是加密过的
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean result = passwordEncoder.matches(user.getPassword(), encodedPassword);
        if (!result) {
            throw new RuntimeException("登录失败，用户密码错误");
        }
        // 生成令牌
        HashMap<String, Object> map = new HashMap<>(2);
        // 将用户的Id保存至token中并加密
        map.put("userId", selected.getUserId());
        String token = JwtUtil.generateToken(map);
        return token;
    }

    @Override
    public Boolean changePassword(User user) {
        // 密码加密
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        User updateUserInfo = new User();
        updateUserInfo.setPassword(encodedPassword);
        // 从上下文对象里面获取用户id，而不是用户传过来的
        Long userId = ContextHolder.getUserId();
        updateUserInfo.setUserId(userId);
        return updateById(updateUserInfo);
    }
}
