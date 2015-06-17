package surveypark.service;

import java.util.List;
import java.util.Set;

import surveypark.domain.security.Role;

public interface RoleService extends BaseService<Role> {

	public void saveOrUpdateEntity(Role model, Integer[] ownRightIds);

	public List<Role> findRolesNotInRange(Set<Role> roles);
	public List<Role> findRolesInRange(Integer[] ownRoleIds);
    
}
