package springmvc.controller;

import org.springframework.web.servlet.mvc.SimpleFormController;

import springmvc.domain.Person;
/*
 * �Զ��������
 */
public class MyFormController extends SimpleFormController{
    //ע�����������������
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
