package cn.spring.hibernate.transaction.annotation;


import org.junit.Test;

public class test extends SpringHelper{
static{
	path="cn/spring/hibernate/transaction/annotation/applicationContext.xml";
}
@Test
public void test(){
	Person person=new Person();
	person.setPid(2);
	person.setPname("qq");
	PersonService personService=(PersonService) context.getBean("personService");
	personService.savePerson(person);
}
}
