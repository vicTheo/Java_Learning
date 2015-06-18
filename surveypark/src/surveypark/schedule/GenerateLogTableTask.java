package surveypark.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import surveypark.service.LogService;
import surveypark.utils.LogUtil;
/*
 * 使用spring的石英调度器动态生成日志表
 */
public class GenerateLogTableTask extends QuartzJobBean {
	//logService
    private LogService logService;
	public LogService getLogService() {
		return logService;
	}
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		String tableName=LogUtil.generateLogTableName(1);
		String sql="create table if not exists "+tableName+" like logs";
        logService.executeSql(sql);
        System.out.println(tableName+"生成完毕");
        
        tableName=LogUtil.generateLogTableName(2);
        sql="create table if not exists "+tableName+" like logs";
        logService.executeSql(sql);
        System.out.println(tableName+"生成完毕");
	}

}
