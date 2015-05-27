package cn.spring.mvc_annotation;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("personService")
public class PersonServiceImpl implements PersonService {
	@Resource(name="personDao")
private PersonDao personDao;
	
	public PersonDao getPersonDao() {
		return personDao;
	}
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}
	public void savePerson() {
		// TODO Auto-generated method stub
		this.personDao.savePerson();
	}
}
