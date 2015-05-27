package cn.spring.mvc_annotation;

import org.springframework.stereotype.Repository;

@Repository("personDao")
public class PersonDaoimpl implements PersonDao{
    
	public void savePerson() {
		// TODO Auto-generated method stub
		System.out.println("save--person");
	}

}
