package user;

import java.util.Collection;
import java.util.Set;

import org.junit.Test;

import OAproject.Domain.Department;
import OAproject.Domain.Menuitem;
import OAproject.Domain.User;
import OAproject.Service.DepartmentService;
import OAproject.Service.UserService;
import OAproject.util.OAUtils;
import base.BaseTest;

public class UserTest extends BaseTest{
 @Test
 public void test(){
	 DepartmentService  depSer=(DepartmentService) context.getBean("departmentService");
//	 PostService  postSer=(PostService) context.getBean("postService");
	 Collection<Department> depList=depSer.getAllEntry();
// 	Collection<Post> postList=postSer.getAllEntry();
 	
 	System.out.println(depList.size());
// 	ActionContext.getContext().put("postList", postList);
// 	ActionContext.getContext().put("deptList", depList);
// 	UserService<User> us=(UserService<User>) context.getBean("userService");
// 	User user=new User();
// 	user.setUid(1L);
// 	user.setUsername("gggggggggg");
// 	us.updateEntry(user);
 	
 
 }
}
