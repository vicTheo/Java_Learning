package surveypark.service;

import java.util.List;

import surveypark.domain.Answer;
import surveypark.domain.Page;
import surveypark.domain.Question;
import surveypark.domain.Survey;
import surveypark.domain.User;

public interface SurveyService {
public Survey newSurvey(User user);
public List<Survey> getMySurveys(User user);
public Survey getById(Integer id);
public Survey getSurveyWithChildren(Integer id);
public void updateSurvey(Survey survey);
public void saveOrUpdatePage(Page page);
/*
 * 编辑页标题
 */
public Page getPageById(Integer pid);

public void saveOrUpdateQuestion(Question question);
public Question getQuestionById(Integer qid);
//删除问题
public void deleteQuestion(Integer qid);
//删除页
public void deletePage(Integer pid);
//删除调查
public void deleteSurvey(Integer sid);
//清除答案
public void clearAnswers(Integer sid);
//改变调查状态
public void toogleStatus(Integer sid);
public void updateLogoPhotoPath(Integer sid, String string);
public List<Survey> findSurveysWithPage(User user);
//移动或复制
public void moveOrCopyPage(Integer srcPid, Integer targetId, Integer pos);
public List<Survey> getAllAvaliableSurvey();
/**
 * 查询调查首页
 */
public Page  getFirstPage(Integer id);
public Page getNextPage(Integer currPid);
public Page getPrePage(Integer currPid);
public void saveAnswer(List<Answer> answers);
}
