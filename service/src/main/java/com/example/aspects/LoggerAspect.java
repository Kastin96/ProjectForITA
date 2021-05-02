package com.example.aspects;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggerAspect {

    @Pointcut("execution(* com.example.controllers.*.*.*(..))")
    public void controllers(){
    }

    @Before("controllers()")
    public void beforeControllers(JoinPoint joinPoint){
        log.info("Log before use: {}", joinPoint.getSignature().getName());
        log.info("-----------");
    }

    @After("controllers()")
    public void afterControllers(JoinPoint joinPoint){
        log.info("Log after use: {}", joinPoint.getSignature().getName());
        log.info("Path: {}", joinPoint.toLongString());
        Arrays.stream(joinPoint.getArgs())
                .forEach(arg -> log.info(arg.toString()));
        log.info("-----------");
    }

//    @Around("controllers()")
//    @SneakyThrows
//    public Object aroundControllers(ProceedingJoinPoint joinPoint){
//        log.info("Log around before use: {}", joinPoint.getSignature().getName());
//
//        final Object result = joinPoint.proceed();
//
//        log.info("Log around after use: {}", joinPoint.getSignature().getName());
//        log.info("-----------");
//        return result;
//    }
}
