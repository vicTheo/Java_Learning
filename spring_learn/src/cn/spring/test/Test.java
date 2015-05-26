package cn.spring.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;



public class Test  extends SpringHelper{
	  
	  static{
		   path="cn/spring/test/applicationContext.xml";
	   }
   @org.junit.Test
   public void test(){
	   //SpringHelper.init();
	   ClassPathXmlApplicationContext applicationcontext=(ClassPathXmlApplicationContext) context;
	   HelloWorld helloworld1=(HelloWorld) applicationcontext.getBean("helloworldFactory");
	  //HelloWorld helloworld2=(HelloWorld) context.getBean("小子");
	    helloworld1.hello();
	   //System.out.println(helloworld1);
	   //applicationcontext.close();
	  //System.out.println(helloworld2);
   }
}
