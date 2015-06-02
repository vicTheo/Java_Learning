package person;


import org.junit.Test;

import OAproject.Dao.PersonDao;
import OAproject.Domain.Person;
import OAproject.Service.PersonService;



public class TransactionTest extends BaseTest{
	/*û�����񻷾�  ���������Person person=(Person) this.getHibernateTemplate().load(Person.class,2);
      session�͹ر���
 * 
 */
	@Test
  public void testHibernateTemplate(){
	  PersonDao personDao=(PersonDao) context.getBean("personDao");
	  Person person=personDao.getPersonById(2);
	  System.out.println(person.getPname());
  }
  
	/*
	 *���������������񻷾��������귽����session�ر� 
	 */
  @Test
  public void testService(){
	  PersonService personService=(PersonService) context.getBean("personService");
	  Person person=personService.getPersonById(2);
	  //System.out.println(person.getPname());
  }
}
