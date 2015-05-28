package cn.spring.jdbc.transaction;

import javax.sql.DataSource;

import org.junit.Test;

public class test extends SpringHelper{
static{
	path="cn/spring/jdbc/transaction/applicationContext.xml";
}
@Test
public void test(){
	PersonService personService=(PersonService) context.getBean("personService");
	System.out.println(personService);
	personService.savePerson();
	
}
}
