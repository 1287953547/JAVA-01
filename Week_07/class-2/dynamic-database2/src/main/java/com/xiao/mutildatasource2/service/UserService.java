package com.xiao.mutildatasource2.service;


import com.xiao.mutildatasource2.dao.UserDao;
import com.xiao.mutildatasource2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<User> selectAll() {
        return dao.selectAll();
    }

    public User selectByid(int id) {
        return dao.selectByid(id);
    }
}
