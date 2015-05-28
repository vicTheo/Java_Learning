package cn.spring.spring_proxy.aop.exception;

public class MyExeception {
  public void throwing(Throwable exec){
	  System.out.println(exec.getMessage());
  }
}
