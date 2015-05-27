package cn.spring.extend;



public class Test extends SpringHelper{
	static{
		path="cn/spring/extend/applicationContext.xml";
	}
	@org.junit.Test
    public void test(){
	Student student=(Student) context.getBean("student");
		     student.say();
	}
	
}
