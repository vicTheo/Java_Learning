package surveypark.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import surveypark.dao.BaseDao;
import surveypark.domain.log.Log;
@Repository("logDao")
public class LogDaoImpl extends BaseDaoImpl<Log> implements BaseDao<Log> {

}
