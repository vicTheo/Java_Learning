package surveypark.action.listener;

import javax.annotation.Resource;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import surveypark.service.LogService;
import surveypark.utils.LogUtil;
@Component
public class InitLogTableListener implements ApplicationListener<ApplicationEvent>{
    @Resource(name="logService")
	private LogService logService;
	public void onApplicationEvent(ApplicationEvent arg0) {
		if(arg0 instanceof ContextRefreshedEvent){
			String tableName=LogUtil.generateLogTableName(0);
			String sql="create table if not exists "+tableName+" like logs";
	        logService.executeSql(sql);
	        System.out.println(tableName+"初始化生成完毕");
	        
	        tableName=LogUtil.generateLogTableName(1);
	        sql="create table if not exists "+tableName+" like logs";
	        logService.executeSql(sql);
	        System.out.println(tableName+"初始化生成完毕");
		}
		
	}

}
