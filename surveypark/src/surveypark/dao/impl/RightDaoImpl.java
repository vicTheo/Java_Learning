package surveypark.dao.impl;

import org.springframework.stereotype.Repository;

import surveypark.dao.BaseDao;
import surveypark.domain.security.Right;
@Repository("rightDao")
public class RightDaoImpl extends BaseDaoImpl<Right> implements BaseDao<Right> {

}
