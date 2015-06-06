package login;

import org.junit.Test;

import base.BaseTest;

import OAproject.Domain.User;
import OAproject.Service.LoginService;

public class LoginTest extends BaseTest {

	@Test
	public void test(){
		LoginService loginService=(LoginService) context.getBean("loginService");
			User user =new User();
			user.setUsername("admin");
			user.setPassword("admin");
		System.out.println(loginService.checkUandP(user).getUsername());
	}
}
