package springmvc.service;

import java.util.List;

import springmvc.domain.Person;

public interface PersonService {

public void save(Person person);
public void update(Person person);
public Person getById(String id);
public void delete(Person person);
public List<Person> getPersonList();
}
