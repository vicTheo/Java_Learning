package OAproject.Service;

import java.io.Serializable;

import OAproject.Domain.Person;

public interface PersonService {
	public void savePerson(Person person);
	public Person getPersonById(Serializable id);
}
