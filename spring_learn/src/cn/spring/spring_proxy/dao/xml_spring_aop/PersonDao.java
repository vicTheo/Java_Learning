package cn.spring.spring_proxy.dao.xml_spring_aop;

import java.util.List;

public interface PersonDao {
public void savePerson();
public void deletePerson();
public void updatePerson();
public  List<Person> getPerson();
}
