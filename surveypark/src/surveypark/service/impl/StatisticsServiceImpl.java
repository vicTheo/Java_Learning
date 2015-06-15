package surveypark.service.impl;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;
import org.springframework.stereotype.Service;

import surveypark.dao.BaseDao;
import surveypark.domain.Answer;
import surveypark.domain.OptionStatisticsModel;
import surveypark.domain.Question;
import surveypark.domain.QuestionStatisticsModel;
import surveypark.service.StatisticsService;
@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {
    @Resource(name="answerDao")
    private BaseDao<Answer> answerDao;
    @Resource(name="questionDao")
    private BaseDao<Question> questionDao;
	/**
	 * 统计问题,返回问题统计模型
	 */
	public QuestionStatisticsModel statistics(Integer qid) {
		Question q=questionDao.getEntity(qid);
		QuestionStatisticsModel qsm=new QuestionStatisticsModel();
		String hql="select count(*) from Question q where q.id=?";
	    int count=((Long)questionDao.uniqueResult(hql, qid)).intValue();
	    qsm.setCount(count);//回答该问题的人数
	    qsm.setQuestion(q);
	    
	    //选项统计hql
	    String hql2="select count(*) from Answer a where a.questionId=? and concat(',',a.answerIds,',') like ?";
	    int qt=q.getQuestionType();
	    switch(qt){
	    //非矩阵选项
	    case 1:
	    case 2:
	    case 3:
	    case 4:
	    case 5:
	    	OptionStatisticsModel osm=null;
	    	String[] options=q.getOptionsArr();
	    	for(int i=0;i<options.length;i++){
	    		osm=new OptionStatisticsModel();
	    		int coun=((Long)answerDao.uniqueResult(hql2, qid,"%,"+i+",%")).intValue();
	    		osm.setCount(coun);
	    		osm.setOptionIndex(i);
	    		osm.setOptionLabel(options[i]);
	    		qsm.getOsms().add(osm);
	    	}
	    	if(q.isOther()){
	    		osm=new OptionStatisticsModel();
	    		int coun=((Long)answerDao.uniqueResult(hql2, qid,"%other%")).intValue();
	    		osm.setCount(coun);
	    		osm.setOptionLabel("其他");
	    		qsm.getOsms().add(osm);
	    	}
	    	break;
	    	//矩阵选项
	    case 6:
	    case 7:
	    case 8:
	    	String[] colArr=q.getMatrixColTitlesArr();
	    	String[] rowArr=q.getMatrixRowTitlesArr();
	    	String[] selectArr=q.getMatrixSelectOptionsArr();
	    	for(int i=0;i<rowArr.length;i++){
	    		for(int j=0;j<colArr.length;j++){
	    			if(qt!=8){//radio|checkbox
	    				osm=new OptionStatisticsModel();
	    				osm.setMatrixColLabel(colArr[j]);
	    				osm.setMatrixColIndex(j);
	    				osm.setMatrixRowIndex(i);
	    				osm.setMatrixRowLabel(rowArr[i]);
	    				count=((Long)answerDao.uniqueResult(hql2, qid,"%,"+i+"_"+j+",%")).intValue();
	    				osm.setCount(count);
	    				qsm.getOsms().add(osm);
	    			}else{
	    				for(int k=0;k<selectArr.length;k++){
	    					osm=new OptionStatisticsModel();
		    				osm.setMatrixColLabel(colArr[j]);
		    				osm.setMatrixColIndex(j);
		    				osm.setMatrixRowIndex(i);
		    				osm.setMatrixRowLabel(rowArr[i]);
		    				osm.setMatrixSelectIndex(k);
		    				osm.setMatrixSelectLabel(selectArr[k]);
		    				count=((Long)answerDao.uniqueResult(hql2, qid,"%,"+i+"_"+j+"_"+k+",%")).intValue();
		    				osm.setCount(count);
		    				qsm.getOsms().add(osm);
	    				}
	    			}
	    		}
	    	}
	    	break;
	    }
		return qsm;
	}

}
