package com.hs.lunpair.core.aop.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@Component
@Aspect
public class RequestLogAspect {

    @Pointcut("execution(* com.hs.lunpair.domain.*.controller.*.*(..))")
    public void loggerPointCut(){
    }

    @Around("loggerPointCut()")
    public Object printRequestLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        try{
            Object result = proceedingJoinPoint.proceed();
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            String controllerName = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
            String methodName = proceedingJoinPoint.getSignature().getName();

            try{
                log.info("=================  Request Log Start  =================");
                log.info("Request Controller => {}", controllerName);
                log.info("Request Method => {}", methodName);
                log.info("Request URL => {}", request.getRequestURL());
                log.info("Request HTTP Method => {}",request.getMethod());
                log.info("Request Log Time => {}", LocalDateTime.now());
                log.info("=================   Request Log End   =================");
            }catch(Exception e){
                log.error("LoggerAspect error", e);
            }

            return result;
        } catch (Throwable throwable) {
            throw throwable;
        }
    }
}
