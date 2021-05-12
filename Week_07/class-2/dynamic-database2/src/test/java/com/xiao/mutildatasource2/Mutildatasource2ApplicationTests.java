package com.xiao.mutildatasource2;

import com.xiao.mutildatasource2.entity.User;
import com.xiao.mutildatasource2.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Mutildatasource2ApplicationTests {
    @Autowired
    private UserService service;

    @Test
    void contextLoads() {
    }

    @Test
    void testSlave() {
        User user = service.selectByid(22);
        System.out.println(user);
    }

}
