package test;

import org.hibernate.SessionFactory;
import org.junit.Test;

import test.base.BaseTest;


public class SessoinFactoryTest extends BaseTest{

	@Test
	public void test(){
		
		SessionFactory sessionFactory=(SessionFactory) context.getBean("sessionFactory");
		System.out.println(sessionFactory.getClass());
	}
}
