package cn.spring.mvc;

public class PersonServiceImpl implements PersonService {
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
