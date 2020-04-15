package com.ctbu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ctbu.mapper")
public class BeadhouseMasterApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeadhouseMasterApplication.class, args);
        System.out.println("项目启动成功！");
    }

}
