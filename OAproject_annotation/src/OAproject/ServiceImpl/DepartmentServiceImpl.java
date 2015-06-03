package OAproject.ServiceImpl;


import java.io.Serializable;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import OAproject.Dao.DepartmentDao;
import OAproject.Domain.Department;
import OAproject.Service.DepartmentService;


@Service("departmentService")
public class DepartmentServiceImpl  implements DepartmentService<Department>{
	@Resource(name="departmentDao")
	private DepartmentDao departmentDao;

	public void saveEntry(Department t) {
		// TODO Auto-generated method stub
		this.departmentDao.saveEntry(t);
	}

	public void updateEntry(Department t) {
		// TODO Auto-generated method stub
		this.departmentDao.updateEntry(t);
	}

	public void deleteEntryById(Serializable id) {
		// TODO Auto-generated method stub
		this.departmentDao.deleteEntryById(id);
	}

	public Department getEntryById(Serializable id) {
		// TODO Auto-generated method stub
		
		return (Department) this.departmentDao.getDEntryById(id);
	}

	public Collection<Department> getAllEntry() {
		// TODO Auto-generated method stub
		return this.departmentDao.getAllEntry();
	}
	
}
