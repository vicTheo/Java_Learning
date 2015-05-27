package cn.spring.mvc_annotation;

import org.junit.Test;

public class test extends SpringHelper{
  static{
	  path="cn/spring/mvc_annotation/applicationContext.xml";
  }
  @Test
  public void test(){
	  PersonAction pa=(PersonAction) context.getBean("personAction");
	  pa.savePerson();
  }
}
