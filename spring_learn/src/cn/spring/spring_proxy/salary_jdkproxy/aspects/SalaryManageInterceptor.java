package cn.spring.spring_proxy.salary_jdkproxy.aspects;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class SalaryManageInterceptor implements InvocationHandler{
   private Logger logger;
   private Security security;
   private Privilege privilege;
   private SalaryManager target;
   private List<Interceptor> interceptorList;
   public SalaryManageInterceptor(Logger logger,Security security,Privilege privilege,SalaryManager target,List<Interceptor> interceptorList){
	   this.logger=logger;
	   this.privilege=privilege;
	   this.security=security;
	   this.target=target;
	   this.interceptorList=interceptorList;
   }
  
public Object invoke(Object proxy, Method method, Object[] args)
		throws Throwable {
	   for(Interceptor inte:interceptorList){
		   inte.interceptor();
	   }
	   if("admin".equals(this.privilege.getAccess())){
		   method.invoke(this.target, args);
	   }else{
		   System.out.println("你还没有权限");
	   }

	
	return null;
}
}
