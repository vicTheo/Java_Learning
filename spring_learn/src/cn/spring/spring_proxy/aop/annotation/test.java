package cn.spring.spring_proxy.aop.annotation;



import org.junit.Test;

public class test extends SpringHelper{
	static{
		path="cn/spring/spring_proxy/aop/annotation/applicationContext.xml";
	}
  @Test
  public void test(){
   PersonDaoImpl personDao=(PersonDaoImpl) context.getBean("personDao");
   personDao.savePerson();
  }

}
