package com.santhosh.accounts.aspect;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.santhosh.accounts.util.Log;

@Aspect
@Component
public class LoggingAspect {
	
	@Log
	Logger logger;
	
	@Before("execution(* com.santhosh.accounts.dao.*.*(..))")
	public void logBefore(JoinPoint joinPoint) {

		logger.info("logBefore() is running!");
		logger.info("hijacked : " + joinPoint.getSignature().getName());
		logger.info("******");
	}
	
	@After("execution(* com.santhosh.accounts.dao.*.*(..))")
	public void logAfter(){
		logger.info("@After:"+new Date());
	}
	
	/*@Around("execution(* com.santhosh.accounts.dao.*.*(..))")
	public void userAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
		System.out.println("@Around: Before calculation-"+ new Date());
		joinPoint.proceed();
		System.out.println("@Around: After calculation-"+ new Date());
	}*/
	
	@AfterReturning(pointcut = "execution(* com.santhosh.accounts.dao.*.*(..))",
			returning="val")
	public void logAfterReturning(Object val){
		logger.info("Method return value:"+ val);
		logger.info("@AfterReturning:"+new Date());
	}
	
	@AfterThrowing(pointcut = "execution(* com.santhosh.accounts.dao.*.*(..))",
			throwing="exception")
	public void logAfterThrowing(Exception exception){
		logger.info("@AfterReturning:"+new Date());
		logger.info("Exception caught:"+ exception.getMessage());
	}
}
