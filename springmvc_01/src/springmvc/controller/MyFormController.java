package springmvc.controller;

import org.springframework.web.servlet.mvc.SimpleFormController;

import springmvc.domain.Person;
/*
 * 自定义控制器
 */
public class MyFormController extends SimpleFormController{
    //注册命令类和命令名称
	public MyFormController(){
		this.setCommandClass(Person.class);
		this.setCommandName("person");
	}
	
	
	@Override
	protected void doSubmitAction(Object command) throws Exception {
		// TODO Auto-generated method stub
		Person person=(Person) command;
		System.out.println("doSubmitAction"+person);
		super.doSubmitAction(command);
	}

}
