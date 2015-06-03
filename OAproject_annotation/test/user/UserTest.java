package user;

import java.util.Collection;

import org.junit.Test;

import com.opensymphony.xwork2.ActionContext;

import OAproject.Domain.Department;
import OAproject.Domain.Post;
import OAproject.Service.DepartmentService;
import OAproject.Service.PostService;
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
 	
 }
}
