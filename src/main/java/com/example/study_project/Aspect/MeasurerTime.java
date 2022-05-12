package com.example.study_project.Aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component

public class MeasurerTime {

    @Around("@annotation(com.example.study_project.Annotation.Timer)")
    public Object logCheckingTime(ProceedingJoinPoint point) throws Throwable{
        long afterStart = System.currentTimeMillis();
        Object proceed = point.proceed();
        long beforeStart =  System.currentTimeMillis() - afterStart;
        log.info(point.getSignature() + " исполнен за " + beforeStart + "ms");
        return proceed;
    }
}

