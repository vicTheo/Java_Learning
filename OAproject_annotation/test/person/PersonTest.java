package person;

import org.junit.Test;

import OAproject.Domain.Person;
import OAproject.Service.PersonService;

public class PersonTest extends BaseTest{
	
	@Test
	public void test(){
		PersonService personService=(PersonService) context.getBean("personService");
		System.out.println(personService);
		 Person person=new Person();
		   person.setPname("ccc");
		   personService.savePerson(person);
	}

}
