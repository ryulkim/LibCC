package com.library.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Aspect
//@Component
public class TimeCheckLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(TimeCheckLoggingAspect.class);

    // MemberController 패키지 내 모든 메서드에 적용 (예시)
    //@Around("execution(* com.demo.scc.member.presentation.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis(); // 시작 시간 기록

        Object result;
        try {
            result = joinPoint.proceed(); // 메서드 실행
        } finally {
            long endTime = System.currentTimeMillis(); // 종료 시간 기록
            long executionTime = endTime - startTime; // 실행 시간 계산

            // 로그 출력
            logger.info("Method [{}] executed in {} ms",
                    joinPoint.getSignature().toShortString(), executionTime);
        }

        return result; // 원래 메서드 결과 반환
    }
}
