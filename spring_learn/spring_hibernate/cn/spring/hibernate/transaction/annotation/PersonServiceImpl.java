package cn.spring.hibernate.transaction.annotation;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("personService")
public class PersonServiceImpl implements PersonService{
	@Resource(name="personDao")
    private PersonDao personDao;
   
     @Transactional(readOnly=false)
	public void savePerson(Person person) {
		this.personDao.savePerson(person);
	}

}
