package com.dhcc.zpc.business.example.controller;

import com.dhcc.zpc.business.example.dto.MfsTaskDto;
import com.dhcc.zpc.business.example.entity.TcUser;
import com.dhcc.zpc.business.example.service.TcUserService;
import com.dhcc.zpc.constant.BusinessCodeEnum;
import com.dhcc.zpc.util.execption.BusinessServiceException;
import com.dhcc.zpc.util.execption.JsonParseException;
import com.dhcc.zpc.util.execption.ParamValidateException;
import com.dhcc.zpc.util.resp.AppResultBuilder;
import com.dhcc.zpc.util.resp.ResponseEntity;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TcUserService tcUserService;

    @PostMapping("/login")
    public ResponseEntity login(TcUser tcUser){
        tcUserService.insert(tcUser);
        return AppResultBuilder.success("登陆成功");
    }

    @GetMapping("/getUser")
    public ResponseEntity<MfsTaskDto> getUser(String idNo){
        return AppResultBuilder.success();
    }
}
