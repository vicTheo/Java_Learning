package surveypark.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import surveypark.domain.Page;
import surveypark.domain.Survey;
import surveypark.service.SurveyService;

@Controller("pageAction")
@Scope("prototype")
public class PageAction extends BaseAction<Page>{
    @Resource(name="surveyService")
	private SurveyService surveyService;
    private Integer sid;
    private Integer pid;
    
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
    
	public String toEditPage(){
		
		return "addPagePage";
	}
	
	public String saveOrUpdatePage(){
		Survey survey=new Survey();
		survey.setId(sid);
		this.getModel().setSurvey(survey);
		surveyService.saveOrUpdatePage(this.getModel());
		return "designSurveyAction";
	}
	/*
	 * 编辑页标题
	 */
	public String editPage(){
		this.model=surveyService.getPageById(pid);
		return "editPage";
	}
	//删除页
	public String deletePage(){
		surveyService.deletePage(pid);
		return "designSurveyAction";
	}
}
