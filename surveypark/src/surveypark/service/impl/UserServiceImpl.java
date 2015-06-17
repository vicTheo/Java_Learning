package surveypark.service.impl;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import surveypark.dao.BaseDao;
import surveypark.domain.User;
import surveypark.domain.security.Role;
import surveypark.service.RoleService;
import surveypark.service.UserService;
import surveypark.utils.StringUtil;
import surveypark.utils.ValidateUtil;
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
    /*覆盖父的getDao()方法
     * 目的是覆盖resource注解
     */
	@Resource(name="roleService")
	private RoleService roleService;
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
		 User user=ValidateUtil.isValid(list)?null:list.get(0);
		
		return user;
	}
    //保存授权
	public void updateAuthorize(User model, Integer[] ownRoleIds) {
		if(!ValidateUtil.isValid(ownRoleIds)){
			model.getRoles().clear();
		}else{
	    List<Role> list=roleService.findRolesInRange(ownRoleIds);
		model.setRoles(new HashSet<Role>(list));
		this.saveOrUpdateEntity(model);
		}
	}
   //清除授权
	public void clearAuthorization(Integer userid){
		this.getEntity(userid).getRoles().clear();
	}

	@Override
	public User getEntity(Integer id) {
		User user=super.getEntity(id);
		user.getRoles().size();
		return user;
	}
   
}
