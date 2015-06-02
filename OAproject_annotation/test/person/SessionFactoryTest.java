package person;

import org.hibernate.SessionFactory;
import org.junit.Test;

import OAproject.Domain.Person;

public class SessionFactoryTest extends BaseTest{
	@Test
	public void test (){
		SessionFactory sessionFactory=(SessionFactory) context.getBean("sessionFactory");
	   System.out.println(sessionFactory);
	  
	}

}
