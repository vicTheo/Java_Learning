package s2sh.action;

import s2sh.domain.Person;
import s2sh.service.PersonService;

public class PersonAction {
  private PersonService personService;

public PersonService getPersonService() {
	return personService;
}

public void setPersonService(PersonService personService) {
	this.personService = personService;
}
  public String savePerson(){
	  Person person=new Person();
	  person.setPname("ff");
	  this.personService.savePerson(person);
	  return "success";
  }
}
