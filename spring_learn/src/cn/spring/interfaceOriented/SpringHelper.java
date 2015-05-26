package cn.spring.interfaceOriented;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHelper {
 public ApplicationContext context;
 public static String path;
 @Before
 public void init(){
	 context=new ClassPathXmlApplicationContext(path);
 }
}
