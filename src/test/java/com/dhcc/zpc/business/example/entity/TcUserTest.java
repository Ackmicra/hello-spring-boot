package com.dhcc.zpc.business.example.entity;


import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TcUserTest {

    public static void main(String[] args) {
        distinctListTest();
    }

    public static void distinctListTest(){
        List<TcUser> userList = new ArrayList<TcUser>();
        TcUser tcUser = new TcUser();
        tcUser.setCifName("张三");
        tcUser.setIdNo("123465");
        tcUser.setPhoneNo("789");
        userList.add(tcUser);

        TcUser tcUser1 = new TcUser();
        tcUser1.setCifName("张三");
        tcUser1.setIdNo("123465");
        tcUser1.setPhoneNo("7891");
        userList.add(tcUser1);

        TcUser tcUser2 = new TcUser();
        tcUser2.setCifName("张三");
        tcUser2.setIdNo("1234652");
        tcUser2.setPhoneNo("789");
        userList.add(tcUser2);

        userList.stream().distinct().forEach(System.out :: println);
    }
}