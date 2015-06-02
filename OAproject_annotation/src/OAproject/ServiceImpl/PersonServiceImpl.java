package OAproject.ServiceImpl;

import java.io.Serializable;

import OAproject.Dao.PersonDao;
import OAproject.Domain.Person;
import OAproject.Service.PersonService;

public class PersonServiceImpl implements PersonService{
    private PersonDao personDao;
    
	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public void savePerson(Person person) {
      this.personDao.savePerson(person);		
	}
   
	public Person getPersonById(Serializable id) {
    Person person=this.personDao.getPersonById(id);
    System.out.println(person.getPname());
		return person;
	}

}
