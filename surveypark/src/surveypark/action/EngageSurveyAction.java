package surveypark.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import surveypark.domain.Answer;
import surveypark.domain.Page;
import surveypark.domain.Survey;
import surveypark.service.SurveyService;
import surveypark.utils.StringUtil;
import surveypark.utils.ValidateUtil;
@Controller("engageSurveyAction")
@Scope("prototype")
public class EngageSurveyAction extends BaseAction<Survey> implements ServletContextAware,SessionAware,ParameterAware{
    
	private static final long serialVersionUID = -5300186882604304499L;
	private final String CURRENT_SURVEY="current_survey";
	private final String ALL_PARAMS="all_params";
	private List<Survey> surveys;
	private Page currPage;
	private Integer sid;
	private Integer currPid;
	
    public Integer getCurrPid() {
		return currPid;
	}
	public void setCurrPid(Integer currPid) {
		this.currPid = currPid;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Page getCurrPage() {
		return currPage;
	}
	public void setCurrPage(Page currPage) {
		this.currPage = currPage;
	}
	public List<Survey> getSurveys() {
		return surveys;
	}
	public void setSurveys(List<Survey> surveys) {
		this.surveys = surveys;
	}
	@Resource(name="surveyService")
	private SurveyService surveyService;
	//接受sc
	private ServletContext sc;

	private Map<String, Object> sessionMap;
	private Map<String, String[]> paramsMap;

	//注入servletContext
	public void setServletContext(ServletContext context) {
      this.sc=context;		
	}
    public String toEngageSurveyList(){
    	this.surveys=surveyService.getAllAvaliableSurvey();
    	return "engageSurveyList";
    }
	/**
	 * 取得logo图表的url地址
	 */
	public String getLogoUrl(String logoPhotoPath){
		if(ValidateUtil.isValid(logoPhotoPath)){
			String realPath = sc.getRealPath(logoPhotoPath);
			if(new File(realPath).exists()){
				return sc.getContextPath() + logoPhotoPath ;
			}
		}
		return sc.getContextPath() + "/question.bmp" ;
	}
	
	public String entry(){
		this.currPage=surveyService.getFirstPage(sid);
		sessionMap.put("current_survey", currPage.getSurvey());
		sessionMap.put(ALL_PARAMS, new HashMap<Integer,Map<String,String[]>>());
		return "engageSurvey";
	}
	public void setSession(Map<String, Object> session) {
		this.sessionMap=session;
	}
	
	//处理参与调查
	public String doEngageSurvey(){
		String submitName=getSubmitName();
		if(submitName.endsWith("next")){
			mergeParamsMapInToSession();
			this.currPage=surveyService.getNextPage(currPid);
			return "engageSurvey";
		}else if(submitName.endsWith("pre")){
			mergeParamsMapInToSession();
			this.currPage=surveyService.getPrePage(currPid);
			return "engageSurvey";
		}else if(submitName.endsWith("done")){
			mergeParamsMapInToSession();
			surveyService.saveAnswer(processAnswers());
			
			return "engageSurveyListAction";
		}else if(submitName.endsWith("exit")){
			clearSessionData();
			return "engageSurveyListAction";
		}
		return null;
	}
	private List<Answer> processAnswers() {
		//矩阵式单选按钮
	   Map<Integer, String> matrixRadioMap = new HashMap<Integer, String>();
		List<Answer> answerList=new ArrayList<Answer>();
		Answer answer=null;
		String key=null;
		String[] value=null;
		for(Map<String,String[]> map:getAllParamsMap().values()){
			for(Entry<String,String[]> entry:map.entrySet()){
				key=entry.getKey();
				value=entry.getValue();
				if(key.startsWith("q")){
					if(!key.contains("_")&&!key.contains("other")){
						//不包括其他项和矩阵式单选的答案
						answer=new Answer();
						answer.setQuestionId(getQuestionId(key));
						answer.setAnswerIds(StringUtil.arr2string(value));
						answer.setSurveyId(getCurrSurveyId());
						//处理带其他项的答案
						String[] otherValue=map.get(key+"other");
						answer.setOtherAnswer(StringUtil.arr2string(otherValue));
						answerList.add(answer);
					
						//处理矩阵式单选按钮
					}else if(key.contains("_")){
							Integer qid=getMatrixRadioQid(key);
							String olderValue=matrixRadioMap.get(qid);
							if(olderValue==null){
								matrixRadioMap.put(qid, StringUtil.arr2string(value));
							}else{
								matrixRadioMap.put(qid,olderValue+","+StringUtil.arr2string(value));
							}
						
					}
				}
			}
		}
		//单独处理矩阵式单选
		processMatrixRadio(answerList,matrixRadioMap);
		return answerList;
	}
	//单独处理矩阵式单选
	private void processMatrixRadio(List<Answer> answerList,
			Map<Integer, String> matrixRadioMap) {
		Integer key=null;
		String value=null;
		Answer a=null;
		for(Entry<Integer,String> entry:matrixRadioMap.entrySet()){
			key=entry.getKey();
			value=entry.getValue();
			a=new Answer();
			a.setAnswerIds(value);
			a.setQuestionId(key);
			a.setSurveyId(getCurrSurveyId());
			answerList.add(a);
		}
		
	}
	private Integer getMatrixRadioQid(String key) {
		
		return Integer.parseInt(key.substring(1, key.indexOf("_")));
	}
	//得到当前surveyid
	private Integer getCurrSurveyId() {
		return ((Survey)sessionMap.get(CURRENT_SURVEY)).getId();
	}
	//获得问题id
	private Integer getQuestionId(String key) {
		return Integer.parseInt(key.substring(1));
	}
	//清理session
	private void clearSessionData(){
		sessionMap.remove(CURRENT_SURVEY);
		sessionMap.remove(ALL_PARAMS);
	}
	
	//将数据合并到session中
	private void mergeParamsMapInToSession() {
		Map<Integer,Map<String,String[]>> allParamsMap=getAllParamsMap();
		allParamsMap.put(currPid, paramsMap);
	}
	private Map<Integer, Map<String, String[]>> getAllParamsMap() {
		
		return (Map<Integer, Map<String, String[]>>) sessionMap.get(ALL_PARAMS);
	}
	//获得提交按钮的名字
	private String getSubmitName() {
		for(String name:paramsMap.keySet()){
			if(name.startsWith("submit_")){
				return name;
			}
		}
		return null;
	}
	//注入parameterMap
	public void setParameters(Map<String, String[]> parameters) {
		this.paramsMap=parameters;
	}
	

	//设置选中标记
	public String setTag(String name,String value,String tag){
		Integer currPid=currPage.getId();
		Map<String,String[]> map=getAllParamsMap().get(currPid);
		String[] values=map.get(name);
		if(StringUtil.contains(values,value)){
			return tag;
		}
		return "";
	}
	
	public String setText(String name){
		Integer currPid=currPage.getId();
		Map<String,String[]> map=getAllParamsMap().get(currPid);
		String[] values=map.get(name);
		if(ValidateUtil.isValid(values)){
			return "value='"+values[0]+"'";
		}
		return "";
	}
}
