package cn.spring.scan;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test extends SpringHelper{
   static{
	   path="cn/spring/scan/applicationContext.xml";
   }
	@Test
	public void test(){
		Student student=(Student) context.getBean("student");
		student.hello();
		
	}
	
}
