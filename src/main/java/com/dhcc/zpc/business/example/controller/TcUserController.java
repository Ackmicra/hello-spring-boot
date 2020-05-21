package com.dhcc.zpc.business.example.controller;

import com.dhcc.zpc.business.example.dto.MfsTaskDto;
import com.dhcc.zpc.business.example.entity.TcUser;
import com.dhcc.zpc.util.execption.BasicException;
import com.dhcc.zpc.util.execption.status.ExceptionStatusEnum;
import com.dhcc.zpc.util.resp.AppResultBuilder;
import com.dhcc.zpc.util.resp.ResponseEntity;
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
    public ResponseEntity login(TcUser tcUser){
        try{
            int k = 1/0;
        } catch (Exception e){
            throw new BasicException(ExceptionStatusEnum.NOT_FOUND, "分母不能为0", e);
//            throw new MyException(ResultCodeEnum.FAIL.code, "分母不能为0", e);
        }
        return AppResultBuilder.makeOKRsp("登陆成功");
    }

    @GetMapping("/getUser")
    public ResponseEntity<MfsTaskDto> getUser(String idNo){
        MfsTaskDto mfsTaskDto = new MfsTaskDto();
        mfsTaskDto.setIdNo("12345651611561616");
        mfsTaskDto.setCifName("张三");
        mfsTaskDto.setPhoneNo("12346578979");
        return AppResultBuilder.response().okQuery(mfsTaskDto);
    }
}
