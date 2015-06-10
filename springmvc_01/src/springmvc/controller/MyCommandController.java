package springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import springmvc.domain.Person;
/*
 * �Զ��������
 */
public class MyCommandController extends AbstractCommandController {
    
	/*ͨ��������ע�����������������
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractCommandController#handle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	public MyCommandController(){
		this.setCommandClass(Person.class);//ע��������
		this.setCommandName("person");//ע����������
	}
	@Override
	protected ModelAndView handle(HttpServletRequest arg0,
			HttpServletResponse arg1, Object obj, BindException arg3)
			throws Exception {
		// TODO Auto-generated method stub
		Person person=(Person) obj;
		System.out.println(person.toString());
		return new ModelAndView("command");
	}

}
