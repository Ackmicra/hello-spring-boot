package com.dhcc.zpc.business.example.service;

import com.dhcc.zpc.business.example.entity.TcUser;
import com.dhcc.zpc.business.example.mapper.TcUserMapper;
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

    public void insert(TcUser tcUser) {
        tcUserMapper.insert(tcUser);
    }

    void deleteUser(String idNo){
        tcUserMapper.deleteUser(idNo);
    }
}
