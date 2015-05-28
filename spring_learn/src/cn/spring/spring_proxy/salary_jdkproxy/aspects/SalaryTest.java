package cn.spring.spring_proxy.salary_jdkproxy.aspects;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SalaryTest {
   @Test
   public void test(){
	   Logger logger=new Logger();
	   Security security=new Security();
	   Privilege privilege =new Privilege();
	   privilege.setAccess("admin");
	   SalaryManager target=new SalaryManagerImpl();
	   List<Interceptor> interceptorList=new ArrayList<Interceptor>();
	   interceptorList.add(logger);
	   interceptorList.add(security);
	   SalaryManageInterceptor si=new SalaryManageInterceptor(logger, security, privilege,target,interceptorList);
	   SalaryManager proxy=(SalaryManager) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), si);
	   proxy.showSalary();
   }
}
