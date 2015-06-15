package surveypark.dao.impl;

import org.springframework.stereotype.Repository;

import surveypark.dao.BaseDao;
import surveypark.domain.Answer;
@Repository("answerDao")
public class AnswerDaoImpl extends BaseDaoImpl<Answer> implements
		BaseDao<Answer> {

}
