package OAproject.DaoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import OAproject.Dao.LoginDao;
import OAproject.DaoImpl.BaseDaoImpl.BaseDaoImpl;
import OAproject.Domain.User;
@Repository("loginDao")
public class LoginDaoImpl extends BaseDaoImpl<User> implements LoginDao{

	public User checkUandP(User user) {
		// TODO Auto-generated method stub
		List<User> userList=this.hibernateTemplate.find("from User where username=? and password=?",new Object[]{user.getUsername(),user.getPassword()});
		if(userList.size()!=0){
			return userList.get(0);
		}else{
			return null;
		}
		
	}

}
