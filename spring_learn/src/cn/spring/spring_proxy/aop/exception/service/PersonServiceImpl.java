package cn.spring.spring_proxy.aop.exception.service;

import cn.spring.spring_proxy.aop.exception.bean.PersonDao;

public class PersonServiceImpl implements PersonService{
	private PersonDao personDao;
	

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public void savePerson() throws Exception {
		// TODO Auto-generated method stub
		this.personDao.savePerson();
	}

	public void updatePerson() throws Exception {
		// TODO Auto-generated method stub
		this.personDao.updatePerson();
	}

	public void deletePerson() throws Exception {
		// TODO Auto-generated method stub
		this.personDao.deletePerson();
	}

}
