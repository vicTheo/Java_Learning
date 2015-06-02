package OAproject.Service;

import java.io.Serializable;
import java.util.Collection;

import OAproject.Domain.Department;

public interface DepartmentService {
	 public void saveDepartment(Department department);
	 public void updateDepartment(Department department);
	 public void deleteDepById(Serializable id,String deleteMode);
	 public Department getDepById(Serializable id);
	 public Collection<Department> getAllDepartment();
}
