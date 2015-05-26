package cn.spring.test;

public class HelloWorldFactory {
 public static HelloWorld getInstance(){
	 System.out.println("factory");
	 return new HelloWorld();
 }
}
