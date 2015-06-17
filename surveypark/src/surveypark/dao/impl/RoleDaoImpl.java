package surveypark.dao.impl;

import org.springframework.stereotype.Repository;

import surveypark.dao.BaseDao;
import surveypark.domain.security.Role;
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements BaseDao<Role> {

}
