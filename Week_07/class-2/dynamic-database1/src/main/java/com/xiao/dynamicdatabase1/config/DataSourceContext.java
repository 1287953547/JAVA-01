package com.xiao.dynamicdatabase1.config;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Mr.xiao
 * @create 2021-05-10 11:36
 */
@Slf4j
public class DataSourceContext {
    public static final ThreadLocal<DBTypeEnum> contextHolder=new ThreadLocal<>();

    public static void setDBTypeEnum(DBTypeEnum value) {
        if (value == null) {
            log.error("数据访问指定路径为空");
            throw new NullPointerException();
        }
        log.info("数据访问指定路径为：{}",value);
        contextHolder.set(value);
    }
    public static DBTypeEnum getDBTypeEnum(){
        return contextHolder.get()==null?DBTypeEnum.MASTER:DBTypeEnum.SLAVE;
    }

    public static void clear() {
        contextHolder.remove();
    }
}
