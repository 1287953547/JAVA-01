package com.example.horizontalshardingjdbcdemo.mapper;

import com.example.horizontalshardingjdbcdemo.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Mr.xiao
 * @create 2021-05-13 15:11
 */
@Mapper
public interface OrderDao {
    @Insert("insert into orders(id,user_id) values(#{id},#{userId})")
    void insert(Order order);
    @Select("select * from orders where id=#{id}")
    Order selectById(Long id);
    @Select("select * from orders where user_id=#{userId}")
    Order selectByUserId(Integer userId);
}
