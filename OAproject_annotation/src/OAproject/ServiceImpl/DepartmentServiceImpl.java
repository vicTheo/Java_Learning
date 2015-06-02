package OAproject.ServiceImpl;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import OAproject.Dao.DepartmentDao;
import OAproject.Domain.Department;
import OAproject.Service.DepartmentService;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService{
	
	@Resource(name="departmentDao")
	private DepartmentDao departmentDao;
	


    @Transactional(readOnly=false)
	public void saveDepartment(Department department) {
		// TODO Auto-generated method stub
		this.departmentDao.saveEntry(department);
	}
    @Transactional(readOnly=false)
	public void updateDepartment(Department department) {
		// TODO Auto-generated method stub
		this.departmentDao.updateEntry(department);
	}
    @Transactional(readOnly=false)
	public void deleteDepById(Serializable id,String deleteMode) {
		// TODO Auto-generated method stub
		this.departmentDao.deleteEntryById(id);
	}

	public Department getDepById(Serializable id) {
		// TODO Auto-generated method stub
		Department department=(Department) this.departmentDao.getDEntryById(id);
		return department;
	}

	public Collection<Department> getAllDepartment() {
		// TODO Auto-generated method stub
		
		return this.departmentDao.getAllEntry();
	}

}
