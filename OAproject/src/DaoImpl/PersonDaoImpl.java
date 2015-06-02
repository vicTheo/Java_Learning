package DaoImpl;

import java.io.Serializable;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import Dao.PersonDao;
import Domain.Person;

public class PersonDaoImpl extends HibernateDaoSupport implements PersonDao {

	public void savePerson(Person person) {
		this.getHibernateTemplate().save(person);
		
	}
 
	public Person getPersonById(Serializable id) {
		//Person person=(Person) this.getHibernateTemplate().get(Person.class,2);
		Person person=(Person) this.getHibernateTemplate().load(Person.class,2);
		
		return person;
	}

}
