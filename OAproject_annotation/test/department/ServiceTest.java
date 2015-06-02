package department;

import org.junit.Test;



import OAproject.Domain.Department;
import OAproject.Service.DepartmentService;
import base.BaseTest;

public class ServiceTest extends BaseTest{
	
	@Test
	public void testService(){
		Department department=new Department();
		department.setDname("ddgdfgdfg");
		DepartmentService ds=(DepartmentService) context.getBean("departmentService");
	    ds.saveDepartment(department);
	    //ds.deleteDepById(1L,DeleteMode.DEL);
	}

}
