package com.spring.transactional.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.management.remote.rmi.RMIConnectionImpl;
import java.lang.reflect.Proxy;
import java.sql.Connection;

@Aspect
@Component
public class DataSourceAspect {

    /*
    Invoke this method when there is a call to getConnection(), close(), commit(), rollBack() of connection object. We want to see when internally these methods are getting called.
     */
    @Around("target(javax.sql.DataSource)")
    public Object logDataSourceConnectionInfo(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("DataSource Tracker: " + proceedingJoinPoint.getSignature());
        Object proceed = proceedingJoinPoint.proceed(); // this will return the Connection
        System.out.println(proceed);
        return proceed;
    }

}
