package surveypark.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import surveypark.dao.BaseDao;
import surveypark.domain.Answer;
import surveypark.domain.Page;
import surveypark.domain.Question;
import surveypark.domain.Survey;
import surveypark.domain.User;
import surveypark.service.SurveyService;
import surveypark.utils.DataUtil;
import surveypark.utils.ValidateUtil;
@Service("surveyService")
public class SurveyServiceImpl implements SurveyService {
     @Resource(name="surveyDao")
	private BaseDao<Survey> surveyDao;
     @Resource(name="pageDao")
	private BaseDao<Page> pageDao;
     @Resource(name="questionDao")
     private BaseDao<Question> questionDao;
     @Resource(name="answerDao")
     private BaseDao<Answer> answerDao;
	/*
    *新建调查
     */
	public Survey newSurvey(User user) {
     Survey survey=new Survey();
     Page page=new Page();
     survey.getPages().add(page);
     survey.setUser(user);
     page.setSurvey(survey);
     
     surveyDao.saveEntity(survey);
     pageDao.saveEntity(page);
     return survey;
	}
	
	public List<Survey> getMySurveys(User user) {
		String hql="from Survey s where s.user.id=?";
		
		return surveyDao.findEntityByHql(hql, user.getId());
	}

	public Survey getById(Integer id) {
		Survey survey=(Survey) surveyDao.getEntity(id);
		return survey;
	}
    /*
     * 将survey的集合属性也查询出来，也可以解决懒加载问题
     */
	public Survey getSurveyWithChildren(Integer id) {
		//调用本类的方法，遵循软件工程的高低原则
		Survey survey=this.getById(id);
		Set<Page> set=survey.getPages();
		for(Page page:set){
			page.getQuestions().size();
		}
		return survey;
	}

	public void updateSurvey(Survey survey) {
		surveyDao.updateEntity(survey);
	}

	public void saveOrUpdatePage(Page page) {
		pageDao.saveOrUpdateEntity(page);
		
	}
	/*
	 * 编辑页标题
	 */
	public Page getPageById(Integer pid) {
		
		return (Page) pageDao.getEntity(pid);
	}

	public void saveOrUpdateQuestion(Question question) {
		questionDao.saveOrUpdateEntity(question);
	}

	public Question getQuestionById(Integer qid) {
		
		return questionDao.getEntity(qid);
	}
	//删除问题

	public void deleteQuestion(Integer qid) {
		String hql="delete from Answer a where a.questionId=?";
		answerDao.batchEntityByHql(hql, qid);
		hql="delete from Question where id=?";
		questionDao.batchEntityByHql(hql, qid);
		
	}
	//删除页
	public void deletePage(Integer pid){
		String hql="delete from Answer a where a.questionId in(select q.id from Question q where q.page.id=?)";
	    answerDao.batchEntityByHql(hql, pid);
	    hql="delete from Question q where q.page.id=?";
	    questionDao.batchEntityByHql(hql, pid);
	    hql="delete from Page where id=?";
	    pageDao.batchEntityByHql(hql, pid);
	}
	//删除调查
	public void deleteSurvey(Integer sid){
		String hql="delete from Answer a where a.surveyId=?";
	    answerDao.batchEntityByHql(hql, sid);
	    //hibernate不支持两级以上写操作 所以不能用 delete from Question q where q.page.survey.id=?
	    hql="delete from Question q where q.page.id in(select p.id from Page p where p.survey.id=?)";
	    questionDao.batchEntityByHql(hql, sid);
	    hql="delete from Page p where p.survey.id=?";
	    pageDao.batchEntityByHql(hql, sid);
	    hql="delete from Survey where id=?";
	    surveyDao.batchEntityByHql(hql, sid);
	}
	
	//清除答案
	public void clearAnswers(Integer sid){
		String hql="delete from Answer a where a.surveyId=?";
		answerDao.batchEntityByHql(hql, sid);
	}
	//改变调查状态
	public void toogleStatus(Integer sid){
		Survey survey=surveyDao.getEntity(sid);
		String hql="update Survey set closed=? where id=?";
		surveyDao.batchEntityByHql(hql, !survey.getClosed(),sid);
	}

	public void updateLogoPhotoPath(Integer sid, String string) {
	String hql="update Survey set logoPhotoPath=? where id=?";
	surveyDao.batchEntityByHql(hql, string,sid);
		
	}
	/**
	 * 查询调查,携带page集合
	 */
	public List<Survey> findSurveysWithPage(User user){
		String hql = "from Survey s where s.user.id = ?"; 
		List<Survey> list = surveyDao.findEntityByHql(hql,user.getId());
		//强行初始化页面集合
		for(Survey s: list){
			s.getPages().size();
		}
		return list ;
	}
	//移动或复制页
	public void moveOrCopyPage(Integer srcPid, Integer targetId, Integer pos){
		Page srcPage=pageDao.getEntity(srcPid);
		Survey survey1=srcPage.getSurvey();
		Page targetPage=pageDao.getEntity(targetId);
		Survey survey2=targetPage.getSurvey();
		if(survey1.getId().equals(survey2.getId())){
			//移动
			setOrderNum(srcPage,targetPage,pos);
		}else{
			//深度复制
			srcPage.getQuestions().size();//初始化question
			Page copy=(Page) DataUtil.deeplyCopy(srcPage);
			//重新设置关系
			copy.setSurvey(survey2);
			pageDao.saveEntity(copy);
			for(Question q:copy.getQuestions()){
				questionDao.saveEntity(q);
			}
			setOrderNum(copy, targetPage, pos);
		}
	}

	private void setOrderNum(Page srcPage, Page targetPage, Integer pos) {
		if(pos==0){//之前
			if(isFirstPage(targetPage)){//目标页是首页
				srcPage.setOrderNum(targetPage.getOrderNum()-0.01f);
			}else{
				srcPage.setOrderNum((targetPage.getOrderNum()+getPrePage(targetPage).getOrderNum())/2);
			}
		}else{//之后
			if(isLastPage(targetPage)){//目标页是尾页
				srcPage.setOrderNum(targetPage.getOrderNum()+0.01f);
			}else{
				srcPage.setOrderNum((targetPage.getOrderNum()+getNextPage(targetPage).getOrderNum())/2);
			}
		}
		
	}
	//得到后一页
    private Page getNextPage(Page targetPage) {
    	String hql="from Page p where p.orderNum >? and p.survey.id=? order by p.orderNum asc";
		List<Page> list=pageDao.findEntityByHql(hql, targetPage.getOrderNum(),targetPage.getSurvey().getId());
		return list.get(0);
		
	}
   //判断是否为尾页
	private boolean isLastPage(Page targetPage) {
		String hql="from Page p where p.orderNum >? and p.survey.id=?";
		List<Page> list=pageDao.findEntityByHql(hql, targetPage.getOrderNum(),targetPage.getSurvey().getId());
		return ValidateUtil.isValid(list);
	}

	//获得前一页
	private Page getPrePage(Page targetPage) {
		String hql="from Page p where p.orderNum <? and p.survey.id=? order by p.orderNum desc";
		List<Page> list=pageDao.findEntityByHql(hql, targetPage.getOrderNum(),targetPage.getSurvey().getId());
		return list.get(0);
	}
     //判断是否为首页
	private boolean isFirstPage(Page targetPage) {
		String hql="from Page p where p.orderNum <? and p.survey.id=?";
		List<Page> list=pageDao.findEntityByHql(hql, targetPage.getOrderNum(),targetPage.getSurvey().getId());
		return ValidateUtil.isValid(list);
	}

	public List<Survey> getAllAvaliableSurvey() {
		String hql="from Survey s where s.closed=?";
		return surveyDao.findEntityByHql(hql, false);
	}
	/**
	 * 查询调查首页
	 */
	public Page  getFirstPage(Integer id){
		String hql="from Page p where p.survey.id=? order by orderNum asc";
		Page page=pageDao.findEntityByHql(hql, id).get(0);
		page.getQuestions().size();
		page.getSurvey().getTitle();
		return page;
	}

	public Page getNextPage(Integer currPid) {
		Page p=pageDao.getEntity(currPid);
		Page page=getNextPage(p);
		page.getSurvey().getTitle();
		page.getQuestions().size();
		return page;
	}

	public Page getPrePage(Integer currPid) {
		Page p=pageDao.getEntity(currPid);
		Page page=getPrePage(p);
		page.getSurvey().getTitle();
		page.getQuestions().size();
		return page;
	}
     //保存答案
	public void saveAnswer(List<Answer> answers) {
		String uid=UUID.randomUUID().toString();
		for(Answer a:answers){
			a.setUuid(uid);//批次
			a.setAnswerTime(new Date());
			answerDao.saveEntity(a);
		}
	
	}
}
