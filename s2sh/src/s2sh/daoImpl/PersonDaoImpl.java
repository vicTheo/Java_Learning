package s2sh.daoImpl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import s2sh.dao.PersonDao;
import s2sh.domain.Person;

public class PersonDaoImpl  extends HibernateDaoSupport implements PersonDao{

	public void savePerson(Person person) {
		//int i=1/0;
		this.getHibernateTemplate().save(person);
	}

}
