package com.dhcc.zpc.business.controller;

import com.dhcc.zpc.business.entity.TcUser;
import io.swagger.annotations.Api;
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
@Api(description = "TcUser(用户管理) API",tags = {"用户管理"})
public class TcUserController {

    @PostMapping("/login")
    public String login(TcUser tcUser){
        return "登陆成功";
    }

    @GetMapping("/get")
    public String get(String idNo){
        return "success";
    }
}
