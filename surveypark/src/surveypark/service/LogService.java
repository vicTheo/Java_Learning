package surveypark.service;

import java.util.List;

import surveypark.domain.log.Log;

public interface LogService extends BaseService<Log> {

public List<Log> findNearestLogs();

}
