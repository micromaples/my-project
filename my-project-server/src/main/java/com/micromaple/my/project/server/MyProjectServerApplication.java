package com.micromaple.my.project.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.micromaple.my.project.server.mapper")
public class MyProjectServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyProjectServerApplication.class, args);
    }
}
