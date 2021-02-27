package com.example.jdbchomework;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import sun.awt.ConstrainableGraphics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Mr.xiao
 * @create 2021-02-26 20:40
 */
public class JdbcService {
    public static void main(String[] args) throws SQLException {
        HikariConfig config=new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test");
        config.setUsername("root");
        config.setPassword("root");
        config.setDriverClassName("com.mysql.jdbc.Driver");
        Connection connection=null;
        try (HikariDataSource dataSource=new HikariDataSource(config)){
            connection=dataSource.getConnection();
            connection.setAutoCommit(false);
            //增加
            PreparedStatement preparedStatement=connection.prepareStatement("insert into account(name,money) values(?,?) ");
            preparedStatement.setString(1, "xiao");
            preparedStatement.setInt(2,400);
            preparedStatement.execute();
            preparedStatement.close();
            connection.commit();
            //删除
            PreparedStatement pre1=connection.prepareStatement("delete from account where money=900");
            pre1.execute();
            pre1.close();
            connection.commit();



            PreparedStatement pre3=connection.prepareStatement("update account set money=? where id=?");
            for(int i=1;i<3;i++){
                pre3.setInt(1, 30000);
                pre3.setInt(2, i);
                pre3.addBatch();
            }
            pre3.executeBatch();
            pre3.close();
            connection.commit();

            PreparedStatement pre2=connection.prepareStatement("select * from account");
            ResultSet resultSet = pre2.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double money=resultSet.getDouble(3);
                System.out.println("id"+id+" name:"+name+" money"+money);
            }
            pre2.close();


        }finally {
            if(connection!=null)
                connection.close();

        }
    }

}
