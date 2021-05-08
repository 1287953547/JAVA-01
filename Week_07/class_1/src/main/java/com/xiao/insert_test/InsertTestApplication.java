package com.xiao.insert_test;

import com.xiao.insert_test.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InsertTestApplication implements CommandLineRunner {
    @Autowired
    private TradeService service;
    public static void main(String[] args) {
        SpringApplication.run(InsertTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //service.simpleInsert();
        service.ThreadPoolSimpleInsert();
        //service.batchInset();
    }
}
