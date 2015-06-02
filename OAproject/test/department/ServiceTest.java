package department;

import org.junit.Test;

import util.DeleteMode;

import Domain.Department;
import Service.DepartmentService;
import base.BaseTest;

public class ServiceTest extends BaseTest{
	
	@Test
	public void testService(){
		Department department=new Department();
		department.setDname("dd");
		DepartmentService ds=(DepartmentService) context.getBean("departmentService");
	    ds.saveDepartment(department);
	    //ds.deleteDepById(1L,DeleteMode.DEL);
	}

}
