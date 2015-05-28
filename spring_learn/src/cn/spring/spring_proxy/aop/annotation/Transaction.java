package cn.spring.spring_proxy.aop.annotation;

import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component("transaction")
@Aspect
public class Transaction {

	@Pointcut("execution(* cn.spring.spring_proxy.aop.annotation.PersonDaoImpl.*(..))")
	public void personDao() {
	}

	// 前置通知
	@Before("personDao()")
	public void beginTransaction(JoinPoint jointPoint) {
		System.out.println(jointPoint.getArgs());
		String methodName = jointPoint.getSignature().getName();
		System.out.println(methodName);
		System.out.println("beginTransaction");
	}

	// 后置通知
	@AfterReturning(pointcut = "personDao()", returning = "val")
	public void commit(JoinPoint joinPoint, Object val) {
		System.out.println(joinPoint.getArgs());
		String methodName = joinPoint.getSignature().getName();
		System.out.println("commit");
	}

	// 最终通知
	@After("personDao()")
	public void finallyMethod() {
		System.out.println("finaly method");
	}

}
