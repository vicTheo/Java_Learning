package person;


import org.junit.Test;

import OAproject.Dao.PersonDao;
import OAproject.Domain.Person;
import OAproject.Service.PersonService;



public class TransactionTest extends BaseTest{
	/*没有事务环境  程序调用完Person person=(Person) this.getHibernateTemplate().load(Person.class,2);
      session就关闭了
 * 
 */
	@Test
  public void testHibernateTemplate(){
	  PersonDao personDao=(PersonDao) context.getBean("personDao");
	  Person person=personDao.getPersonById(2);
	  System.out.println(person.getPname());
  }
  
	/*
	 *当整个方法有事务环境，调用完方法，session关闭 
	 */
  @Test
  public void testService(){
	  PersonService personService=(PersonService) context.getBean("personService");
	  Person person=personService.getPersonById(2);
	  //System.out.println(person.getPname());
  }
}
