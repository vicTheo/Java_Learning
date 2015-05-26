package cn.spring.DI;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Student {
   private Integer pid;
   private String pname;
   private List<Object> lists;
   private Map<Object,Object> map;
   private Properties props;
   private Person person;
public Integer getPid() {
	return pid;
}
public void setPid(Integer pid) {
	this.pid = pid;
}
public String getPname() {
	return pname;
}
public void setPname(String pname) {
	this.pname = pname;
}
public List<Object> getLists() {
	return lists;
}
public void setLists(List<Object> lists) {
	this.lists = lists;
}
public Map<Object, Object> getMap() {
	return map;
}
public void setMap(Map<Object, Object> map) {
	this.map = map;
}
public Properties getProps() {
	return props;
}
public void setProps(Properties props) {
	this.props = props;
}
public Person getPerson() {
	return person;
}
public void setPerson(Person person) {
	this.person = person;
}
   public void hello(){
	   System.out.println("hello");
   }
}
