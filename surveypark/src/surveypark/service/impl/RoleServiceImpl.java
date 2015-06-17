package surveypark.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import surveypark.dao.BaseDao;
import surveypark.domain.security.Right;
import surveypark.domain.security.Role;
import surveypark.service.RightService;
import surveypark.service.RoleService;
import surveypark.utils.DataUtil;
import surveypark.utils.StringUtil;
import surveypark.utils.ValidateUtil;
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements
		RoleService {
    @Resource(name="rightService")
	private RightService rightService;
	@Override
	@Resource(name="roleDao")
	public void setDao(BaseDao<Role> dao) {
		super.setDao(dao);
	}

	public void saveOrUpdateEntity(Role r, Integer[] ownRightIds) {
	  if(!ValidateUtil.isValid(ownRightIds)){
		  r.getRights().clear();
	  }
		List<Right> list=rightService.findRightsInRange(ownRightIds);
		r.setRights(new HashSet<Right>(list));
		this.saveOrUpdateEntity(r);
	}

	@Override
	public Role getEntity(Integer id) {
		Role role=super.getEntity(id);
		role.getRights().size();
		return role;
	}

	public List<Role> findRolesNotInRange(Set<Role> roles) {
		if(ValidateUtil.isValid(roles)){
			return this.getAllEntities();
		}
		String hql="from Role r where r.id not in("+DataUtil.extractEntitiesIds(roles)+")";
			
		return this.findEntityByHql(hql);
	}

	public List<Role> findRolesInRange(Integer[] ownRoleIds) {
		if(!ValidateUtil.isValid(ownRoleIds)){
			return this.getAllEntities();
		}
		String hql="from Role r where r.id in("+StringUtil.arr2string(ownRoleIds)+")";
	
		return 	this.findEntityByHql(hql);
	}
    
	
}
