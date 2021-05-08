package com.xiao.insert_test.service;

import com.xiao.insert_test.dao.TradeDao;
import com.xiao.insert_test.entity.trade;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.java2d.SurfaceDataProxy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Mr.xiao
 * @create 2021-05-01 22:30
 */
@Service
public class TradeService {
    @Autowired
    private TradeDao dao;
    public void simpleInsert(){
        long startTime=System.currentTimeMillis();
        trade temp=new trade();
        temp.setCreateTime(new Date());
        temp.setGoodsInfo("测试");
        temp.setModifyTime(new Date());
        temp.setTradeId("1");
        temp.setUid("15_1l");
        temp.setStatus("测试状态");
        for(int i=0;i<100000;i++){
            dao.insert(temp);
        }
        long endTime=System.currentTimeMillis();
        System.out.println("单线程单sql插入操作："+(endTime-startTime)/1000+"s");
    }
    /*10个线程分别执行，每个线程1次性执行1000次*/
    public void ThreadPoolSimpleInsert(){
        long startTime=System.currentTimeMillis();
        trade temp=new trade();
        temp.setCreateTime(new Date());
        temp.setGoodsInfo("测试");
        temp.setModifyTime(new Date());
        temp.setTradeId("2");
        temp.setUid("16_1l");
        temp.setStatus("测试状态");
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch latch=new CountDownLatch(10);
        Runnable task=new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10000;i++)
                    dao.insert(temp);
                latch.countDown();
            }
        };
        for (int i = 0; i < 10; i++) {
            executorService.execute(task);
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime=System.currentTimeMillis();
        executorService.shutdown();
        System.out.println("多线程单sql插入操作："+(endTime-startTime)/1000+"s");
    }
    //使用mysql的批插入模式，
    public void batchInset() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/java_course?serverTimezone=UTC&rewriteBatchedStatements=true&characterEncoding=utf-8";
        long start = System.currentTimeMillis();
        try (Connection connection = DriverManager.getConnection(url, "root", "123456");
             PreparedStatement preparedStatement = connection.prepareStatement("insert into trade(trade_id,uid,status,goods_info,pay_time,total_amount,received_amount,fail_msg,create_time,modify_time) values(" +
                     "?,?,?,?,?,?,?,?,?,?)")) {
            for (int i = 1; i <= 100000; i++) {
                preparedStatement.setInt(1, 3);
                preparedStatement.setString(2, "17l");
                preparedStatement.setString(3, "测试状态");
                preparedStatement.setString(4, "测试");
                java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
                preparedStatement.setDate(5, date);
                preparedStatement.setDouble(6, 0);
                preparedStatement.setDouble(7, 0);
                preparedStatement.setString(8, "");
                preparedStatement.setDate(9, date);
                preparedStatement.setDate(10, date);
                preparedStatement.addBatch();
                if (i % 10000 == 0) {
                    preparedStatement.executeBatch();
                }
            }
            long time1 = System.currentTimeMillis();
            System.out.println("使用preparedStatement批量插入10w条记录共耗时：" + (time1 - start) + " ms");

        }
    }
}
