package cn.spring.spring_proxy.salary_cglib_proxy;

import java.lang.reflect.Method;



import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class SalaryManagerInterceptor implements MethodInterceptor {
	 private Logger logger;
	   private Security security;
	   private Privilege privilege;
	   private SalaryManager target;
	   public SalaryManagerInterceptor(Logger logger,Security security,Privilege privilege,SalaryManager target){
		   this.logger=logger;
		   this.privilege=privilege;
		   this.security=security;
		   this.target=target;
	   }
	public Object createProxy(){
		Enhancer enhancer=new Enhancer();
		enhancer.setSuperclass(this.target.getClass());//设置目标类为代理类的父类
		enhancer.setCallback(this);
		return enhancer.create();
		
	}
	public Object intercept(Object arg0, Method method, Object[] args,
			MethodProxy arg3) throws Throwable {
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
