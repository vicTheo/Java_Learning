package cn.spring.spring_proxy.salary_jdkproxy.aspects;

public class Logger implements Interceptor{
 

public void interceptor() {
	 System.out.println("logger----logging");
}
}
