package surveypark.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import surveypark.dao.BaseDao;
import surveypark.domain.User;
import surveypark.service.UserService;
import surveypark.utils.ValidateUtil;
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
    /*覆盖父的getDao()方法
     * 目的是覆盖resource注解
     */
	@Override
	@Resource(name="userDao")
	public void setDao(BaseDao dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}

	public boolean validateEmail(String email) {
		String hql="from User u where u.email=?";
		List<User> list=this.dao.findEntityByHql(hql, email);
		return ValidateUtil.isValid(list);
	}
    /*
     * 验证邮箱和密码
     */
	public User validateEmailAndPwd(String email, String password) {
		String hql="from User u where u.email=? and u.password=?";
		List<User> list=this.dao.findEntityByHql(hql, email,password);
		return ValidateUtil.isValid(list)?null:list.get(0);
	}

	
   
}
