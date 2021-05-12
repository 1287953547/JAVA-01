package com.xiao.mutildatasource2;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;


@SpringBootApplication
@MapperScan(basePackages = "com.xiao.mutildatasource2.dao")
public class Mutildatasource2Application {

    public static void main(String[] args) {
        SpringApplication.run(Mutildatasource2Application.class, args);
    }

}
