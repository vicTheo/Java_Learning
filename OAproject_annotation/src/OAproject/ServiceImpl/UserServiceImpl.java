package OAproject.ServiceImpl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import OAproject.Dao.UserDao;
import OAproject.Domain.User;
import OAproject.Service.UserService;
import OAproject.ServiceImpl.Base.BaseServiceImpl;


public class UserServiceImpl extends BaseServiceImpl<User> implements UserService<User> {
	@Resource(name="userDao")
	private UserDao userDao;
	
  
   public Collection<User> getAllUsers() {
	return this.userDao.getAllUsers();
	}
	
}
