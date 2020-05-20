package com.dhcc.zpc.business.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author 赵朋超
 * @Date 2020/5/19 17:36
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class TcUserController {

    @PostMapping("/login")
    public String login(){
        return "登陆成功";
    }

    @GetMapping("/get")
    public String get(){
        return "success";
    }
}
