package com.linrty.goods.controller;

import com.linrty.goods.bean.User;
import com.linrty.goods.common.CommonResult;
import com.linrty.goods.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @PackageName: com.linrty.book.controller
 * @ClassName: UserController
 * @Description: TODO
 * @author: Linrty
 * @date: 2022/3/24 15:37
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public CommonResult<Boolean> register(@RequestBody User user){
        return CommonResult.success(userService.register(user));
    }

    @PostMapping("/login")
    @ResponseBody
    public CommonResult<String> login(@RequestBody User user) {
        return CommonResult.success(userService.login(user));
    }

    @PostMapping("/changePassword")
    @ResponseBody
    public CommonResult<Boolean> changePassword(@RequestBody User user) {
        return CommonResult.success(userService.changePassword(user));
    }
}
