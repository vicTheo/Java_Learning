package OAproject.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import OAproject.Domain.User;
import OAproject.Service.Base.BaseService;

public interface UserService<T> extends BaseService<T>{
	public Collection<User> getAllUsers();
	public T getUserByName(String username);
}
		
