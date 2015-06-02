package Service;

import java.io.Serializable;

import Domain.Person;

public interface PersonService {
	public void savePerson(Person person);
	public Person getPersonById(Serializable id);
}
