package com.xiao.dynamicdatabase1.service;

import com.xiao.dynamicdatabase1.aop.ReadOnly;
import com.xiao.dynamicdatabase1.dao.UserDao;
import com.xiao.dynamicdatabase1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.xiao
 * @create 2021-05-10 14:37
 */
@Service
public class UserService {
    @Autowired
    UserDao dao;

    public void insert(User user) {
        dao.insert(user);
    }
    @ReadOnly
    public List<User> selectAll() {
        return dao.selectAll();
    }
    @ReadOnly
    public User selectByid(int id) {
        return dao.selectByid(id);
    }
}
