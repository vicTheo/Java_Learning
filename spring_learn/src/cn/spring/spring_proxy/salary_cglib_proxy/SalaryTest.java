package cn.spring.spring_proxy.salary_cglib_proxy;

import org.junit.Test;

public class SalaryTest {
   @Test
   public void test(){
	   Logger logger=new Logger();
	   Security security=new Security();
	   Privilege privilege =new Privilege();
	   SalaryManager target=new SalaryManager();
	   SalaryManagerInterceptor interceptor=new SalaryManagerInterceptor(logger,security,privilege,target);
	   SalaryManager proxy=(SalaryManager) interceptor.createProxy();
	   proxy.showSalary();
   }
}
