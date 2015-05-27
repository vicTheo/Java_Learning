package cn.spring.spring_proxy.dao_jdkproxy;

import java.util.ArrayList;
import java.util.List;

public class PersonDaoImpl implements PersonDao{

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

	public List<Person> getPerson() {
		// TODO Auto-generated method stub
		Person person=new Person("xiaoli",2);
		List<Person> personList=new ArrayList<Person>();
		personList.add(person);
		for(Person per:personList){
			System.out.println(per.getPname());
		}
		return personList;
	}

}
