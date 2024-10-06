package com.usyd.ee5619;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.usyd.ee5619.Mapper")
public class Ee5619Application {

    public static void main(String[] args) {
        SpringApplication.run(Ee5619Application.class, args);
    }

}
