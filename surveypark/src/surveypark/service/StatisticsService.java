package surveypark.service;

import surveypark.domain.QuestionStatisticsModel;

public interface StatisticsService {
	public QuestionStatisticsModel statistics(Integer qid);
}
