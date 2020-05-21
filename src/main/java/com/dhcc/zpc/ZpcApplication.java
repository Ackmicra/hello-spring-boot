package com.dhcc.zpc;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.dhcc.zpc.business.example.mapper")
@EnableSwagger2Doc
public class ZpcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZpcApplication.class, args);
    }

}
