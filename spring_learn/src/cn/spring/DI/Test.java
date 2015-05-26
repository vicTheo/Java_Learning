package cn.spring.DI;

import java.util.List;

public class Test extends SpringHelper{
	static{
		path="cn/spring/DI/applicationContext.xml";
	}
	@org.junit.Test
    public void test(){
		     Student student=(Student) context.getBean("student");
		     student.hello();
		     System.out.println(student.getPname());
		     System.out.println(student.getPid());
		     System.out.println(student.getProps());
		     for(Object list:student.getLists()){
		    	 System.out.println(list);
		     }
		     System.out.println(student.getMap().get("map1"));
		     student.getPerson().helloWorld();
		     
	}
	@org.junit.Test
	public void test1(){
		Person person=(Person) context.getBean("person");
		System.out.println(person.getPid());
		System.out.println(person.getPname());
		person.getStudent().hello();
	}
}
