package cn.spring.mvc_annotation;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

@Controller("personAction")
public class PersonAction{
   
	@Resource(name="personService")
   private PersonService personService;
	
	
	public void savePerson() {
		// TODO Auto-generated method stub
		this.personService.savePerson();
	}
}
