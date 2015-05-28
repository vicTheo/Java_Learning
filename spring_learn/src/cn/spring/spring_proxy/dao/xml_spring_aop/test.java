package cn.spring.spring_proxy.dao.xml_spring_aop;



import org.junit.Test;

public class test extends SpringHelper{
	static{
		path="cn/spring/spring_proxy/dao/xml_spring_aop/applicationContext.xml";
	}
  @Test
  public void test(){
	PersonDao persondao=(PersonDao) context.getBean("personDao");
	persondao.savePerson();
  }

}
