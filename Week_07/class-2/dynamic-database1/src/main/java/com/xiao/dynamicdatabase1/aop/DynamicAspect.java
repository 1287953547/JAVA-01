package com.xiao.dynamicdatabase1.aop;


import com.xiao.dynamicdatabase1.config.DBTypeEnum;
import com.xiao.dynamicdatabase1.config.DataSourceContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Mr.xiao
 * @create 2021-05-09 20:10
 */
@Component
@Aspect
public class DynamicAspect {
    @Around("@annotation(ReadOnly)")//将切点省去，且切点设置和切面设置二合一,二合一导致了从库无法访问
    public Object setRead(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            DataSourceContext.setDBTypeEnum(DBTypeEnum.SLAVE);
            Object[] args = joinPoint.getArgs();
            return  joinPoint.proceed(args);//原来一直没有return产生的结果
        }finally {
            DataSourceContext.clear();
        }
    }
//    @Before("aspect()")
//    public void before(JoinPoint joinPoint) throws NoSuchMethodException {
//        String methodName = joinPoint.getSignature().getName();
//        Class[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
//        Method method = joinPoint.getTarget().getClass().getMethod(methodName, parameterTypes);
//        ReadOnly methodAnnotation = method.getAnnotation(ReadOnly.class);
//        if (methodAnnotation != null) {
//            DataSourceContext.setDBTypeEnum(DBTypeEnum.SLAVE);
//        }
//
//    }
//    @Pointcut("execution(* com.xiao.dynamicdatabase1.service.*.*(..))")
//    private void aspect() {
//
//    }
//
//    public void after(JoinPoint point) {
//        DataSourceContext.clear();
//    }

}
