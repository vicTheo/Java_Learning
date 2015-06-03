package OAproject.ServiceImpl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import OAproject.Dao.DepartmentDao;
import OAproject.Domain.Department;
import OAproject.Service.DepartmentService;
import OAproject.ServiceImpl.Base.BaseServiceImpl;

@Service("departmentSevice")
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService<Department>{
	@Resource(name="departmentDao")
	private DepartmentDao departmentDao;
	
}
