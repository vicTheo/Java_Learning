package springmvc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import springmvc.dao.PersonDao;
import springmvc.domain.Person;
import springmvc.service.PersonService;
@Service(value="personService")
public class PersonServiceImpl implements PersonService{
    @Resource(name="personDao")
	private PersonDao personDao;
	public void save(Person person) {
		// TODO Auto-generated method stub
		this.personDao.save(person);
	}
	public void update(Person person) {
		// TODO Auto-generated method stub
		this.personDao.update(person);
	}
	public Person getById(String id) {
		// TODO Auto-generated method stub
		return this.personDao.getById(id);
	}
	public void delete(Person person) {
		// TODO Auto-generated method stub
		this.personDao.delete(person);
	}
	public List<Person> getPersonList() {
		// TODO Auto-generated method stub
		return this.personDao.getPersonList();
	}
   
}
