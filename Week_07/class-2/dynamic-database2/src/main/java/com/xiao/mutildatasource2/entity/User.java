package com.xiao.mutildatasource2.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Mr.xiao
 * @create 2021-05-10 14:18
 */
@Data
public class User {
    private Integer id;
    private String loginName;
    private String phone;
    private String uid;
    private Date createTime;
    private Date modifyTime;

}
