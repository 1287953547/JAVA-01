package com.xiao.dynamicdatabase1.config;

import lombok.Data;

/**
 * @author Mr.xiao
 * @create 2021-05-09 16:18
 */

public enum DBTypeEnum {
    MASTER,
    SLAVE,
    ;

    public static void main(String[] args) {
        System.out.println(DBTypeEnum.MASTER.name());
    }

}
