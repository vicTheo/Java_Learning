package s2sh.serviceImpl;

import s2sh.dao.PersonDao;
import s2sh.domain.Person;
import s2sh.service.PersonService;

public class PersonServiceImpl implements PersonService {
   private PersonDao personDao;
   
	public PersonDao getPersonDao() {
	return personDao;
}

public void setPersonDao(PersonDao personDao) {
	this.personDao = personDao;
}

	public void savePerson(Person person) {
		// TODO Auto-generated method stub
    this.personDao.savePerson(person);
	}

}
