package cn.spring.spring_proxy.dao_jdkproxy;

import java.lang.reflect.Proxy;

import org.junit.Test;

public class test {
  @Test
  public void test(){
	  Transaction transaction=new Transaction();
	  PersonDaoImpl personDao=new PersonDaoImpl();
	  PersonDaoInterceptor pdi=new PersonDaoInterceptor(transaction, personDao);
	  PersonDao proxy=(PersonDao) Proxy.newProxyInstance(personDao.getClass().getClassLoader(),personDao.getClass().getInterfaces(), pdi);
	  proxy.deletePerson();
	  proxy.getPerson();
  }
}
