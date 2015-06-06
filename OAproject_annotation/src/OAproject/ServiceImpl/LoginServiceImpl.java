package OAproject.ServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import OAproject.Dao.LoginDao;
import OAproject.Domain.User;
import OAproject.Service.LoginService;
@Service("loginService")
public class LoginServiceImpl implements LoginService{
    @Resource(name="loginDao")
	private LoginDao loginDao;
	public User checkUandP(User user) {
		// TODO Auto-generated method stub
		
		return this.loginDao.checkUandP(user);
	}

}
