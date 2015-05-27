package cn.spring.spring_proxy.salary;

import org.junit.Test;

public class SalaryTest {
   @Test
   public void test(){
	   Logger logger=new Logger();
	   Security security=new Security();
	   Privilege privilege =new Privilege();
	   SalaryManager sm=new SalaryManager(logger, security, privilege);
	   sm.showSalary();
   }
}
