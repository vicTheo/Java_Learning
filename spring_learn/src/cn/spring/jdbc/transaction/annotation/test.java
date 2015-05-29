package cn.spring.jdbc.transaction.annotation;

import javax.sql.DataSource;

import org.junit.Test;

public class test extends SpringHelper{
static{
	path="cn/spring/jdbc/transaction/applicationContext.xml";
}
@Test
public void test(){
	//PersonService personService=(PersonService) context.getBean("personService");
    cn.spring.jdbc.PersonService personService2=(cn.spring.jdbc.PersonService) context.getBean("personService2");
	//personService2.savePerson();
	
}
}
