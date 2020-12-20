package com.hs.lunpair.core.aop.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
public class ExceptionLogAspect {
    //AOP : 어떤 로직을 기준으로 핵심적인 관점, 부가적인 관점으로 나누어서 보고 그 관점을 기준으로 모듈화
    //=> 예외 처리의 모듈화

    //Aspect : 위에서 설명한 흩어진 관심사를 모듈화 한 것. 주로 부가기능을 모듈화함.
    //Target : Aspect를 적용하는 곳 (클래스, 메서드 .. )
    //Advice : 실질적으로 어떤 일을 해야할 지에 대한 것, 실질적인 부가기능을 담은 구현체
    //JointPoint : Advice가 적용될 위치, 끼어들 수 있는 지점. 메서드 진입 지점, 생성자 호출 시점, 필드에서 값을 꺼내올 때 등 다양한 시점에 적용가능
    //PointCut : JointPoint의 상세한 스펙을 정의한 것. 'A란 메서드의 진입 시점에 호출할 것'과 같이 더욱 구체적으로 Advice가 실행될 지점을 정할 수 있음

    //도메인 내의 컨트롤러 클래스에 대하여 로그 처리 발생
    @Pointcut("execution(* com.hs.lunpair.domain.*.controller.*.*(..))")
    public void exceptionPointCut(){
    }

    @AfterThrowing(value = "exceptionPointCut()", throwing = "e")
    public void printExceptionLog(JoinPoint jp, Exception e){
        log.warn("=================  Exception Log Start  =================");
        log.warn("| Exception Time => {}", LocalDateTime.now());
        log.warn("| Exception Method => {}", jp.getSignature().getName());
        log.warn("| Exception Message => {}", e.getMessage());
        log.warn("=================   Exception Log End   =================");
    }
}
