package springtest;

import org.junit.Test;

import s2sh.dao.PersonDao;
import s2sh.domain.Person;
import s2sh.service.PersonService;

public class PersonTest extends BaseTest{
	
	@Test
	public void testSavePerson(){
		Person person=new Person();
		person.setPname("sss");
	PersonService personService=(PersonService) context.getBean("personService");
	personService.savePerson(person);
	System.out.println(personService);
	}

}
