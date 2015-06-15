package test;


import org.junit.Test;

import surveypark.domain.QuestionStatisticsModel;
import surveypark.service.StatisticsService;
import test.base.BaseTest;

public class StatisticsTest extends BaseTest{

	@Test
	public void test(){
		StatisticsService ss=(StatisticsService) context.getBean("statisticsService");
		QuestionStatisticsModel qsm=ss.statistics(4);
		System.out.println();
	}
}
