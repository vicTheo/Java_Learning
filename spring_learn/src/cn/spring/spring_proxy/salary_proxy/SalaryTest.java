package cn.spring.spring_proxy.salary_proxy;

import org.junit.Test;

public class SalaryTest {
   @Test
   public void test(){
	   Logger logger=new Logger();
	   Security security=new Security();
	   Privilege privilege =new Privilege();
	   privilege.setAccess("admin");
	   SalaryManager target=new SalaryManagerImpl();
	   SalaryManageProxy sm=new SalaryManageProxy(logger, security, privilege,target);
	   sm.showSalary();
   }
}
