package cn.spring.mvc;

import org.junit.Test;

public class test extends SpringHelper{
  static{
	  path="cn/spring/mvc/applicationContext.xml";
  }
  @Test
  public void test(){
	  PersonAction pa=(PersonAction) context.getBean("personAction");
	  pa.savePerson();
  }
}
