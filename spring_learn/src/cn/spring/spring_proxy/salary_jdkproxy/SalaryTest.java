package cn.spring.spring_proxy.salary_jdkproxy;

import java.lang.reflect.Proxy;

import org.junit.Test;

public class SalaryTest {
   @Test
   public void test(){
	   Logger logger=new Logger();
	   Security security=new Security();
	   Privilege privilege =new Privilege();
	   privilege.setAccess("admin");
	   SalaryManager target=new SalaryManagerImpl();
	   SalaryManageInterceptor si=new SalaryManageInterceptor(logger, security, privilege,target);
	   SalaryManager proxy=(SalaryManager) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), si);
	   proxy.showSalary();
   }
}
