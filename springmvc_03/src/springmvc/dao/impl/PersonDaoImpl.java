package springmvc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import springmvc.dao.PersonDao;
import springmvc.domain.Person;
@Repository(value="personDao")
public class PersonDaoImpl implements PersonDao{
    @Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	public void save(Person person) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(person);
	}
	public void update(Person person) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(person);
	}
	public Person getById(String id) {
		// TODO Auto-generated method stub
		
		return (Person) this.sessionFactory.getCurrentSession().get(Person.class, id);
	}
	public void delete(Person person) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(person);
	}
	public List<Person> getPersonList() {
		// TODO Auto-generated method stub
		List<Person> list=(List<Person>) this.sessionFactory.getCurrentSession().createQuery("from Person").list();
		return list;
	}

}
