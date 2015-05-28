package cn.spring.spring_proxy.dao.xml_spring_aop;

public class Person {
  private String pname;
  private Integer pid;
  public Person(String pname,Integer pid){
	  this.pname=pname;
	  this.pid=pid;
  }
public String getPname() {
	return pname;
}
public void setPname(String pname) {
	this.pname = pname;
}
public Integer getPid() {
	return pid;
}
public void setPid(Integer pid) {
	this.pid = pid;
}
  
}
