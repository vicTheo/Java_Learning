package OAproject.DaoImpl;


import org.springframework.stereotype.Repository;

import OAproject.Dao.DepartmentDao;
import OAproject.DaoImpl.BaseDaoImpl.BaseDaoImpl;
import OAproject.Domain.Department;

@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl<Department>  implements DepartmentDao<Department> {
 
}
