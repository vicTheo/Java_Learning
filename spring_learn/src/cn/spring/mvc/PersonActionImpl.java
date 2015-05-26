package cn.spring.mvc;

public class PersonActionImpl implements PersonAction{
   private PersonService personService;
	
	public PersonService getPersonService() {
		return personService;
	}
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	public void savePerson() {
		// TODO Auto-generated method stub
		this.personService.savePerson();
	}
}
