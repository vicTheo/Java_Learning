package ServiceImpl;

import java.io.Serializable;

import Dao.PersonDao;
import Domain.Person;
import Service.PersonService;

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
