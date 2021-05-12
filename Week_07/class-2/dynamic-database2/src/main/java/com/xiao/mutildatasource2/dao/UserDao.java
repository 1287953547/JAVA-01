package com.xiao.mutildatasource2.dao;


import com.xiao.mutildatasource2.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.xiao
 * @create 2021-05-10 14:26
 */
@Mapper
@Repository
public interface UserDao {
    @Select("select login_name,uid from user")
    List<User> selectAll();
    @Insert("insert into user(login_name,phone,uid,create_time,modify_time) values(#{loginName},#{phone},#{uid},#{createTime},#{modifyTime})")
    void insert(User user);
    @Select("select * from user where id=#{id}")
    User selectByid(int id);
}
