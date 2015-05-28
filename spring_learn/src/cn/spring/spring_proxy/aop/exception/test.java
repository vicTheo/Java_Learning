package cn.spring.spring_proxy.aop.exception;

import org.junit.Test;

import cn.spring.spring_proxy.aop.exception.bean.PersonAction;

public class test extends SpringHelper{
	static{
		path="cn/spring/spring_proxy/aop/exception/applicationContext.xml";
	}
  @Test
  public void test() throws Exception{
	  PersonAction personAction=(PersonAction) context.getBean("personaction");
	  personAction.deletePerson();
  }
}
