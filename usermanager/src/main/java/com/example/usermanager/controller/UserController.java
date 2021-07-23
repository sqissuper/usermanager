package com.example.usermanager.controller;

import com.example.usermanager.mapper.UserMapper;
import com.example.usermanager.model.UserInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ClassName:UserController
 * Package:com.example.usermanager.controller
 * Description:
 *
 * @Author:HP
 * @date:2021/7/22 17:50
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource  //将某个类注入到当前类当中
    private UserMapper userMapper;

    //登录方法
    @RequestMapping("/login")
    public UserInfo login(@RequestParam String username,@RequestParam String password) {
        return userMapper.login(username,password);
    }
}
