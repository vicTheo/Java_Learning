package ServiceImpl;

import java.io.Serializable;
import java.util.Collection;

import Dao.DepartmentDao;
import Domain.Department;
import Service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService{
	private DepartmentDao departmentDao;
	

	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	public void saveDepartment(Department department) {
		// TODO Auto-generated method stub
		this.departmentDao.saveDepartment(department);
	}

	public void updateDepartment(Department department) {
		// TODO Auto-generated method stub
		this.departmentDao.updateDepartment(department);
	}

	public void deleteDepById(Serializable id,String deleteMode) {
		// TODO Auto-generated method stub
		this.departmentDao.deleteDepById(id,deleteMode);
	}

	public Department getDepById(Serializable id) {
		// TODO Auto-generated method stub
		Department department=this.departmentDao.getDepById(id);
		return department;
	}

	public Collection<Department> getAllDepartment() {
		// TODO Auto-generated method stub
		Collection<Department> coll=this.departmentDao.getAllDepartment();
		return coll;
	}

}
