package com.example.horizontalshardingjdbcdemo.service;

import com.example.horizontalshardingjdbcdemo.entity.Order;
import com.example.horizontalshardingjdbcdemo.mapper.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mr.xiao
 * @create 2021-05-13 15:21
 */
@Service
public class OrderService {
    @Autowired
    private OrderDao dao;
    public void insert(Order order) {
        dao.insert(order);
    }
    public Order selectById(Long id){
        return dao.selectById(id);
    }

    public Order selectByUserId(int uerId) {
        return dao.selectByUserId(uerId);
    }
}
