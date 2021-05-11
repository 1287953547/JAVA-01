package com.xiao.dynamicdatabase1.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.xiao
 * @create 2021-05-09 10:44
 */
@Configuration
@Slf4j
public class MutiDataSourceCongfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSourceProperties masterProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource masterDataSource() {
        DataSourceProperties mProperties=masterProperties();
        log.info("master datasource:{}",mProperties.getUrl());
        return mProperties.initializeDataSourceBuilder().build();//实践表明，其需要url的方式配置；
    }

    @Bean
    @ConfigurationProperties("spring.datasource.slave")
    public DataSourceProperties slaveProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource slaveDataSource() {
        DataSourceProperties slavePro=slaveProperties();
        log.info("slave datasource:{}", slavePro.getUrl());
        return slavePro.initializeDataSourceBuilder().build();
    }

//    @Bean(name = "dataSourceMaster")
//    @ConfigurationProperties(prefix = "spring.datasource.master")
//    public DataSource masterDataSource() {
//        return DataSourceBuilder.create().build();
//    }//需要jdbc-url的方式配置;
//
//    @Bean(name = "dataSourceSlave")
//    @ConfigurationProperties(prefix = "spring.datasource.slave")
//    public DataSource slaveDataSource() {
//        return DataSourceBuilder.create().build();
//    }

    @Bean
    @Resource
    public PlatformTransactionManager masterTxManager(DataSource masterDataSource){
        return new DataSourceTransactionManager(masterDataSource);
    }
    @Bean
    @Resource
    public PlatformTransactionManager slaveTxManager(DataSource slaveDataSource){
        return new DataSourceTransactionManager(slaveDataSource);
    }

    @Bean(name="routingDataSource")
    @Primary
    public DataSource routingDataSource() {
        MyRoutingDataSource myRoutingDataSource = new MyRoutingDataSource();
        myRoutingDataSource.setDefaultTargetDataSource(masterDataSource());

        Map<Object,Object> map=new HashMap<>();
        map.put(DBTypeEnum.MASTER.name(),masterDataSource());
        map.put(DBTypeEnum.SLAVE.name(), slaveDataSource());
        myRoutingDataSource.setTargetDataSources(map);
        myRoutingDataSource.afterPropertiesSet();
        return myRoutingDataSource;
    }
//    @Bean
//    @Resource
//    public PlatformTransactionManager routingTxManager(DataSource routingDataSource) {
//        return new DataSourceTransactionManager(routingDataSource);
//    }
    @Bean
    @Resource
    public SqlSessionFactory sqlSessionFactory(DataSource routingDataSource) throws Exception {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(routingDataSource);
        return bean.getObject();
    }
}
