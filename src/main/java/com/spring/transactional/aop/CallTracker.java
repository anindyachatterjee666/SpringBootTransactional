package com.spring.transactional.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CallTracker {

    @Pointcut("within(com..service.*) || within(com..repository.*)")
    public void logMethodPointCut(){

    }

//    @Before("logMethodPointCut()")
//    public void logBeforeMethodCall(){
//        System.out.println("!!! Method Started !!! ");
//    }
//    @After("logMethodPointCut()")
//    public void logAfterMethodCall(){
//        System.out.println("!!! Method Ended !!! ");
//    }
    // combine @Before & @After
    @Around("logMethodPointCut()")
    public Object logBeforeMethodCall(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String name = proceedingJoinPoint.getSignature().getName();

        // Before each method execution
        System.out.println("Method Started : " + name);

        // call to your method
        Object proceed = proceedingJoinPoint.proceed();

        // After each method execution
        System.out.println("Method Ended : " + name);

        return proceed;
    }
}

/*
    The AOP Proxy is not only calling the business method, also it runs the advice before that.
 */