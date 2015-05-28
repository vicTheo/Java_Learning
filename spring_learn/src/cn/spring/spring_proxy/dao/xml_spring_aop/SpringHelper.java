package cn.spring.spring_proxy.dao.xml_spring_aop;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHelper {
  static ApplicationContext context;
  static String path;
  @Before
  public  void init(){
	  context=new ClassPathXmlApplicationContext(path);
  }
}
