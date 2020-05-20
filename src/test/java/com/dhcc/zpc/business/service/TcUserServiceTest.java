package com.dhcc.zpc.business.service;


import com.dhcc.zpc.business.entity.TcUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TcUserServiceTest {

    @Autowired
    private TcUserService tcUserService;
    @Test
    public void insert() {
        TcUser tcUser = new TcUser();
        tcUser.setIdNo("123456");
        tcUser.setCifName("张三");
        tcUser.setPhoneNo("13245687952");
        tcUserService.insert(tcUser);
    }

    @Test
    public void deleteUser(){
        tcUserService.deleteUser("123456");
    }
}