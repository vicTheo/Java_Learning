package surveypark.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import surveypark.dao.BaseDao;
import surveypark.domain.log.Log;
import surveypark.service.LogService;
@Service("logService")
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService {

	@Override
	@Resource(name="logDao")
	public void setDao(BaseDao<Log> dao) {
		super.setDao(dao);
	}
     
}
