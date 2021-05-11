package com.xiao.dynamicdatabase1;

import com.xiao.dynamicdatabase1.dao.UserDao;
import com.xiao.dynamicdatabase1.entity.User;
import com.xiao.dynamicdatabase1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.Date;
import java.util.List;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class/*,
        JdbcTemplateAutoConfiguration.class*/})
@Slf4j
@MapperScan({"com.xiao.dynamicdatabase1.dao"})
public class DynamicDatabase1Application implements CommandLineRunner {
    @Autowired
    private UserService service;
    public static void main(String[] args) {
        SpringApplication.run(DynamicDatabase1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        for(int i=0;i<10;i++){
//            User user = new User();
//            user.setCreateTime(new Date());
//            user.setModifyTime(new Date());
//            user.setPhone("110");
//            user.setLoginName("xiao" + i);
//            user.setUid("test_" + i);
//            service.insert(user);
//            List<User> users = service.selectAll();
//            log.info("打印第{}次记录",i);
//            if (users == null) {
//                log.warn("第{}次查询，从库还为空",i);
//            }else{
//                for (User temp : users) {
//                    log.info(temp.toString());
//                }
//            }
//        }
        //List<User> users = service.selectAll();
        User user=service.selectByid(1);
        log.info(user.toString());

    }
}
