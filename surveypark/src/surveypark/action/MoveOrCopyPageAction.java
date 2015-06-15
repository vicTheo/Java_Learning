package surveypark.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import surveypark.domain.Page;
import surveypark.domain.Survey;
import surveypark.domain.User;
import surveypark.service.SurveyService;
@Controller("moveOrCopyPageAction")
@Scope("prototype")
public class MoveOrCopyPageAction extends BaseAction<Page> implements UserAware {
    
	private static final long serialVersionUID = 1488612252065979541L;
	@Resource(name="surveyService")
	private SurveyService surveyService;
	private List<Survey> surveys;
	private Integer sid;
	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	private Integer srcPid;
	private Integer targetId;
	private Integer pos;
	
	public Integer getSrcPid() {
		return srcPid;
	}

	public void setSrcPid(Integer srcPid) {
		this.srcPid = srcPid;
	}

	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public List<Survey> getSurveys() {
		return surveys;
	}

	public void setSurveys(List<Survey> surveys) {
		this.surveys = surveys;
	}

	private User user;
	public void setUser(User user) {
		this.user=user;
	}
    
	public String toMoveOrCopyPage(){
		this.surveys=surveyService.findSurveysWithPage(user);
		return "toMoveOrCopyPage";
	}
	//移动或复制
    public String doMoveOrCopyPage(){
    	surveyService.moveOrCopyPage(srcPid,targetId,pos);
    	return "designSurveyAction";
    }
}

