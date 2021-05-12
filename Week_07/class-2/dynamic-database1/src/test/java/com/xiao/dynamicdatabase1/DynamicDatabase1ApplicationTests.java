package com.xiao.dynamicdatabase1;

import com.xiao.dynamicdatabase1.entity.User;
import com.xiao.dynamicdatabase1.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DynamicDatabase1ApplicationTests {
    @Autowired
    UserService userService;
    @Test
    void contextLoads() {
        User user = userService.selectByid(22);
        System.out.println(user);
    }

}
