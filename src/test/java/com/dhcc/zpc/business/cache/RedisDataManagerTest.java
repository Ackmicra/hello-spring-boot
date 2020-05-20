package com.dhcc.zpc.business.cache;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDataManagerTest {

    @Autowired
    private RedisDataManager redisDataManager;

    @Test
    public void redisTest(){
        redisDataManager.put("taskId", "123456", 10, TimeUnit.SECONDS);

        String value = (String)redisDataManager.get("taskId");
        System.out.println("redis缓存的值为：" + value);
    }
}