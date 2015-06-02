package Action;

import java.io.Serializable;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import Domain.Person;
import Service.PersonService;

public class PersonAction extends ActionSupport {
  private PersonService personService;

public PersonService getPersonService() {
	return personService;
}

public void setPersonService(PersonService personService) {
	this.personService = personService;
}
  
public String savePerson(){
	Person person=new  Person();
	this.personService.savePerson(person);
	return null;
}

public String getPersonById(){
	Person person= this.personService.getPersonById(2);
	ServletActionContext.getRequest().setAttribute("person", person);
	return SUCCESS;
}
}
