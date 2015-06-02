package OAproject.Dao;

import java.io.Serializable;

import OAproject.Domain.Person;

public interface PersonDao {
public void savePerson(Person person);
public Person getPersonById(Serializable id);
}
