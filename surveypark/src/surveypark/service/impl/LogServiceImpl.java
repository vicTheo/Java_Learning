package surveypark.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.id.UUIDHexGenerator;
import org.springframework.stereotype.Service;

import surveypark.dao.BaseDao;
import surveypark.domain.log.Log;
import surveypark.service.LogService;
import surveypark.utils.LogUtil;
@Service("logService")
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService {
	UUIDHexGenerator id=new UUIDHexGenerator();
	@Override
	@Resource(name="logDao")
	public void setDao(BaseDao<Log> dao) {
		super.setDao(dao);
	}

	@Override
	public void saveEntity(Log t) {
		String sql="insert into "+LogUtil.generateLogTableName(0)+
			"(id,operator,opertime,opername,operparams,operresult,resultmsg) values(?,?,?,?,?,?,?)";
		String uuid=(String) id.generate(null, null);
		this.executeSql(sql,uuid,
				            t.getOperator(),
				            t.getOperTime(),
				            t.getOperName(),
				            t.getOperParams(),
				            t.getOperResult(),
				            t.getResultMsg());
	}
    //查询最近的日志默认是两个月
	public List<Log> findNearestLogs() {
		 String sql="select * from "+LogUtil.generateLogTableName(-1)+" union "
				                 +"select * from "+
	                      LogUtil.generateLogTableName(0);
		 
		return this.findObjectsBySql(sql);
	}
     
}
