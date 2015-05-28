package cn.spring.spring_proxy.aop.xml.salary;



import org.junit.Test;

public class SalaryTest extends SpringHelper{
	static{
		path="cn/spring/spring_proxy/aop/xml/salary/applicationContext.xml";
	}
   @Test
   public void test(){
	   
	   SalaryManagerImpl sm=(SalaryManagerImpl) context.getBean("salaryManager");
	   sm.showSalary();
	   
   }
}
