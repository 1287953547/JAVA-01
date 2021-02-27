package com.example.springstarter.domain;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mr.xiao
 * @create 2021-02-27 10:39
 */
@Configuration(proxyBeanMethods = false)
public class MyAutoConfig {
    @Bean
    @ConditionalOnMissingBean
    public Klass klass(){
        return new Klass();
    }
    @Bean
    @ConditionalOnMissingBean
    public School school(){
        return new School();
    }
    @Bean
    @ConditionalOnMissingBean
    public Student student(){
        return new Student();
    }
}
