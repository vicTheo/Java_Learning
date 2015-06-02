package Dao;

import java.io.Serializable;
import java.util.Collection;

import Dao.BaseDao.BaseDao;
import Domain.Department;

public interface DepartmentDao<T> extends BaseDao<T>{
 public void saveDepartment(Department department);
 public void updateDepartment(Department department);
 public void deleteDepById(Serializable id,String deleteMode);
 public Department getDepById(Serializable id);
 public Collection<T> getAllDepartment();
}
