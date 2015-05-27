package cn.spring.scan;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Student {
  
	
  //@Resource
 @Autowired  //按类型匹配
  //@Qualifier("person")   //按ID匹配
  private Person person;
  
  public void hello(){
	  this.person.dance();
  }

}
