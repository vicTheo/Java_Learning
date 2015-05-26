package cn.spring.test;

public class HelloWorld {
	public HelloWorld(){
		System.out.println("构造");
	}
	public void init(){
		System.out.println("init");
	}
	public void destroy(){
		System.out.println("destroy");
	}
   public void hello(){
	   System.out.println("Hello World");
   }
}
