package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.User;
import com.sangeng.service.LoginServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginServcie loginServcie;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        //登录
        return loginServcie.login(user);
    }

    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('test')")
    public String hello(){
        return "hello";
    }

    @GetMapping("/user/logout")
    public ResponseResult logout(){
        return loginServcie.logout();
    }
}
