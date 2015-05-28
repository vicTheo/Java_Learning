package cn.spring.spring_proxy.dao.xml_spring_aop;


import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class Transaction {
	
	//前置通知
 public void beginTransaction(JoinPoint jointPoint){
	 System.out.println(jointPoint.getArgs());
	 String methodName=jointPoint.getSignature().getName();
	 System.out.println(methodName);
	 System.out.println("beginTransaction");
 }
 
 //后置通知
 public void commit(JoinPoint joinPoint,Object val){
	 List<Person> personList=(List<Person>) val;
	 System.out.println(personList.size());
	 System.out.println("commit");
 }
 //最终通知
 public void finallyMethod(){
	  System.out.println("finally method");
 }
 //异常通知
 public void exceptionMethod(Throwable exec){
	 System.out.println(exec.getMessage());
 }
 //环绕通知
 public void aroundMethod(ProceedingJoinPoint jointPoint) throws Throwable{
	 if("getPerson".equals(jointPoint.getSignature().getName())){
		 jointPoint.proceed();
	 }
 }
}
