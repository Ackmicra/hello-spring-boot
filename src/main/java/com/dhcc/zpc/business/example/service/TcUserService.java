package com.dhcc.zpc.business.example.service;

import com.dhcc.zpc.business.example.entity.TcUser;
import com.dhcc.zpc.business.example.mapper.TcUserMapper;
import com.dhcc.zpc.constant.BusinessCodeEnum;
import com.dhcc.zpc.util.aop.annotation.WsBaseAdvice;
import com.dhcc.zpc.util.execption.BusinessServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author 赵朋超
 * @Date 2020/5/19 17:39
 * @Version 1.0
 */
@Service
public class TcUserService {

    @Autowired
    private TcUserMapper tcUserMapper;

    @WsBaseAdvice
    public void insert(TcUser tcUser) {
        try{
            int k = 1/0;
        } catch (Exception e){
            throw new BusinessServiceException(BusinessCodeEnum.ILLEGAL_DATA, "分母不能为0", e);
        }
        tcUserMapper.insert(tcUser);
    }

    void deleteUser(String idNo){
        tcUserMapper.deleteUser(idNo);
    }
}
