package cn.spring.spring_proxy.aop.exception.bean;

public class PersonDaoImpl implements PersonDao{

	public void savePerson() throws Exception {
		// TODO Auto-generated method stub
		int i=1/0;
	}

	public void updatePerson()throws Exception  {
		// TODO Auto-generated method stub
		int i=1/0;
	}

	public void deletePerson()throws Exception  {
		// TODO Auto-generated method stub
		int i=1/0;
	}

}
