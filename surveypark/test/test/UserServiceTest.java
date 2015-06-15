package test;



import org.junit.Test;

import surveypark.domain.User;
import surveypark.service.UserService;
import test.base.BaseTest;

public class UserServiceTest extends BaseTest {
  
	@Test
	public void test(){
		UserService userService=(UserService) context.getBean("userService");
		User user=new User();
		user.setNickName("ss");
		user.setPassword("123");
		userService.saveEntity(user);
	}
}
