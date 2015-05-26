package cn.spring.DI;

public class Person {
  private Integer pid;
  private String pname;
  private Student student;
  public Person(Integer pid,String pname){
	  this.pid=pid;
	  this.pname=pname;
  }
  public Person(Integer pid,Student student){
	  this.pid=pid;
	  this.student=student;
  }
   public void helloWorld(){
	   System.out.println("hello World");
   }

public Integer getPid() {
	return pid;
}

public String getPname() {
	return pname;
}

public Student getStudent() {
	return student;
}


}
