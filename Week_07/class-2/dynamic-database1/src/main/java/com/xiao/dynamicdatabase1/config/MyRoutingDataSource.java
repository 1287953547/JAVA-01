package com.xiao.dynamicdatabase1.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

/**
 * @author Mr.xiao
 * @create 2021-05-10 15:12
 */
@Slf4j
public class MyRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        DBTypeEnum temp = DataSourceContext.getDBTypeEnum();
        log.info("当前使用的数据源是:"+temp.toString());
        return temp.name();
    }
}
