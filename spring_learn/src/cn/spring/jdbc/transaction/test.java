package cn.spring.jdbc.transaction;


import org.junit.Test;

public class test extends SpringHelper{
static{
	path="cn/spring/jdbc/transaction/annotation/applicationContext.xml";
}
@Test
public void test(){
	cn.spring.jdbc.transaction.annotation.PersonService personService=(cn.spring.jdbc.transaction.annotation.PersonService) context.getBean("personService");
	personService.savePerson();
}
}
