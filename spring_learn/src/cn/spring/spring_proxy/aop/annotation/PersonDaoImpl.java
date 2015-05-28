package cn.spring.spring_proxy.aop.annotation;



import org.springframework.stereotype.Component;

@Component("personDao")
public class PersonDaoImpl{

	public void savePerson() {
		// TODO Auto-generated method stub
		System.out.println("saveperson");
	}

	public void deletePerson() {
		// TODO Auto-generated method stub
		System.out.println("deleteperson");
	}

	public void updatePerson() {
		// TODO Auto-generated method stub
		System.out.println("updateperson");
	}

	
}
