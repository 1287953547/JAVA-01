package com.xiao.insert_test.dao;

import com.xiao.insert_test.entity.trade;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Mr.xiao
 * @create 2021-05-01 22:20
 */
@Mapper
public interface TradeDao {
    @Insert("insert into trade(trade_id,uid,status,goods_info,pay_time,total_amount,received_amount,fail_msg,create_time,modify_time) values" +
            "(#{tradeId},#{uid},#{status},#{goodsInfo},#{payTime},#{totalAmount},#{receivedAmount},#{failMsg},#{createTime},#{modifyTime})")
    public  void insert(trade data);

}
