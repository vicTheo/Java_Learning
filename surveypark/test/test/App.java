package test;

import org.junit.Test;

import surveypark.service.LogService;
import surveypark.utils.LogUtil;
import test.base.BaseTest;

public class App extends BaseTest{
	@Test
	public void test(){
		   LogService logService=(LogService) context.getBean("logService");
		   String tableName=LogUtil.generateLogTableName(1);
		   String sql="create table if not exists "+tableName+" like logs";
		   logService.executeSql(sql);
	}
}
