package cn.spring.hibernate.transaction.xml;

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

}
