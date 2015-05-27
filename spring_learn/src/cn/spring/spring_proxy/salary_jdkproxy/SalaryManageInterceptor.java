package cn.spring.spring_proxy.salary_jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SalaryManageInterceptor implements InvocationHandler{
   private Logger logger;
   private Security security;
   private Privilege privilege;
   private SalaryManager target;
   public SalaryManageInterceptor(Logger logger,Security security,Privilege privilege,SalaryManager target){
	   this.logger=logger;
	   this.privilege=privilege;
	   this.security=security;
	   this.target=target;
   }
  
public Object invoke(Object proxy, Method method, Object[] args)
		throws Throwable {
	this.logger.logging();
	   this.security.secure();
	   if("admin".equals(this.privilege.getAccess())){
		   method.invoke(this.target, args);
	   }else{
		   System.out.println("你还没有权限");
	   }

	
	return null;
}
}
