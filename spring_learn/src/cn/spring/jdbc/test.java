package cn.spring.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test extends SpringHelper{
   static{
	   path="cn/spring/jdbc/applicationContext.xml";
   }
	@Test
	public void testSave(){
		PersonDao personDao=(PersonDao) context.getBean("personDao");
	    //personDao.savePerson();
		
		PersonDao personDao2=(PersonDao) context.getBean("personDao2");
		//personDao2.savePerson();
		
		PersonDao personDao3=(PersonDao) context.getBean("personDao3");
		personDao3.savePerson();
	}
	
	@Test
	public void testQuery(){
		PersonDao personDao3=(PersonDao) context.getBean("personDao3");
		List<Person> list=personDao3.getPerson();
		System.out.println(list.size());
	}
}
