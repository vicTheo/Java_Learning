package cn.spring.hibernate.transaction.xml;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class PersonDaoImpl extends HibernateDaoSupport implements PersonDao{

	public void savePerson(Person person) {
		this.getHibernateTemplate().save(person);
		
	}

}
