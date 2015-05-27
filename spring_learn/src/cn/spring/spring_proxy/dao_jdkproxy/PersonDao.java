package cn.spring.spring_proxy.dao_jdkproxy;

import java.util.List;

public interface PersonDao {
public void savePerson();
public void deletePerson();
public void updatePerson();
public  List<Person> getPerson();
}
