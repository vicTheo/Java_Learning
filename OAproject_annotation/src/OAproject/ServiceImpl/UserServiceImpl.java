package OAproject.ServiceImpl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import OAproject.Dao.UserDao;
import OAproject.Domain.User;
import OAproject.Service.UserService;

@Service("userService")
public class UserServiceImpl  implements UserService<User> {
	@Resource(name="userDao")
	private UserDao userDao;
	
  
   public Collection<User> getAllUsers() {
	return this.userDao.getAllUsers();
	}


public void saveEntry(User t) {
	// TODO Auto-generated method stub
	this.userDao.saveEntry(t);
}


public void updateEntry(User t) {
	// TODO Auto-generated method stub
	this.userDao.updateEntry(t);
}


public void deleteEntryById(Serializable id) {
	// TODO Auto-generated method stub
	this.userDao.deleteEntryById(id);
}


public User getEntryById(Serializable id) {
	// TODO Auto-generated method stub
	return (User) this.userDao.getDEntryById(id);
}


public Collection<User> getAllEntry() {
	// TODO Auto-generated method stub
	return this.userDao.getAllEntry();
}


public User getUserByName(String username) {
	// TODO Auto-generated method stub
	return (User) this.userDao.getUserByName(username);
}
	
}
