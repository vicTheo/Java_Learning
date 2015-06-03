package OAproject.DaoImpl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Repository;

import OAproject.Dao.UserDao;
import OAproject.DaoImpl.BaseDaoImpl.BaseDaoImpl;
import OAproject.Domain.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao<User>{

	public Collection<User> getAllUsers() {
		List<User> userList=this.hibernateTemplate.find("from User u left join fetch u.posts p left join fetch u.department d");
		return new HashSet<User>(userList);
	}

	public User getUserByName(String username) {
	    List<User> list= this.hibernateTemplate.find("from User where username=?",username);
		if(list.size()==0){
			return null;
		}else{
			return list.get(0);
		}
	  
	}

}
