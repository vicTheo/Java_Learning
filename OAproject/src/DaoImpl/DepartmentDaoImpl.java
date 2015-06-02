package DaoImpl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import Dao.DepartmentDao;
import DaoImpl.BaseDaoImpl.BaseDaoImpl;
import Domain.Department;
import Domain.Post;
import Domain.User;

public class DepartmentDaoImpl extends BaseDaoImpl<Department>  implements DepartmentDao<Department> {

	public void saveDepartment(Department department) {
		this.saveEntry(department);
		
	}

	public void updateDepartment(Department department) {
      this.updateEntry(department);	
	}

	public void deleteDepById(Serializable id,String deleteMode) {
	  this.deleteEntryById(id);
		/*Department department=(Department) this.getHibernateTemplate().get(Department.class, id);
		if(department!=null){
		if("del".equals(deleteMode)){//单表删除
			this.getHibernateTemplate().delete(department);
		}else if("del_pre_release".equals(deleteMode)){//删除之前先解除关系
			Set<User> set=department.getUsers();
			for(User user:set){
				user.setDepartment(null);
			}
			this.getHibernateTemplate().delete(department);
		}else{//级联删除 相当于cascade=delete
			Set<User> set=department.getUsers();
			for(User user:set){
				user.setDepartment(null);
				
			Set<Post> postSet=user.getPosts();
			for(Post post:postSet){
				post.setUsers(null);
				this.getHibernateTemplate().delete(post);
			}
			this.getHibernateTemplate().delete(user);
			this.getHibernateTemplate().delete(department);
			}
		}
		}*/
	}

	public Department getDepById(Serializable id) {
		Department department=(Department) this.getDEntryById(id);
		return department;
	}

	public Collection<Department> getAllDepartment() {
		return this.getAllEntry();
		
	}




}
