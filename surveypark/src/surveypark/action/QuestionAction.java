package surveypark.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import surveypark.domain.Page;
import surveypark.domain.Question;
import surveypark.service.SurveyService;
@Controller("questionAction")
@Scope("prototype")
public class QuestionAction extends BaseAction<Question> {

	private static final long serialVersionUID = 2410028287744993667L;
   @Resource(name="surveyService")
	private SurveyService surveyService;
	private Integer sid;
	private Integer pid;
	private Integer qid;
	
	public Integer getQid() {
		return qid;
	}
	public void setQid(Integer qid) {
		this.qid = qid;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	//到达选择问题题型页面
	public String toSelectQuestionTypePage(){
		return "toSelectQuestionTypePage";
	}
	//到达设计问题页面
	public String toDesignQuestionPage(){
		
		return ""+this.getModel().getQuestionType();
	}
	
	//保存问题
	public String saveOrUpdateQuestion(){
		Page page=new Page();
		page.setId(pid);
		model.setPage(page);
		surveyService.saveOrUpdateQuestion(model);
		return "designSurveyAction";
	}
	//编辑问题
	public String editQuestion(){
		this.model=surveyService.getQuestionById(qid);
		return ""+this.model.getQuestionType();
	}
	//删除问题
	public String deleteQuestion(){
		surveyService.deleteQuestion(qid);
	return "designSurveyAction";
	}
}
