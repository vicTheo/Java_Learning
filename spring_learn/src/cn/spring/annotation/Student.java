package cn.spring.annotation;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Student {
  
	
  //@Resource
 //@Autowired  //按类型匹配
  @Qualifier("person")   //按ID匹配
  private Person person;
  
  public void hello(){
	  this.person.dance();
  }
  @PostConstruct
  public void init(){
	  System.out.println("init");
  }
  @PreDestroy
  public void destroy(){
	  System.out.println("destroy");
  }
}
