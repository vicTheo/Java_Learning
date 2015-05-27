package cn.spring.annotation;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test extends SpringHelper{
   static{
	   path="cn/spring/annotation/applicationContext.xml";
   }
	@Test
	public void test(){
		ClassPathXmlApplicationContext ap=(ClassPathXmlApplicationContext) context;
		Student student=(Student) ap.getBean("student");
		student.hello();
		ap.close();
	}
	
}
