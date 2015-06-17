package surveypark.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import surveypark.domain.security.Right;
import surveypark.service.RightService;
@Controller("rightAction")
@Scope("prototype")
public class RightAction extends BaseAction<Right> {

	private static final long serialVersionUID = 259285182648209760L;
	private List<Right> allRights;
	private Integer rightId;
	
    public Integer getRightId() {
		return rightId;
	}

	public void setRightId(Integer rightId) {
		this.rightId = rightId;
	}

	public List<Right> getAllRights() {
		return allRights;
	}

	public void setAllRights(List<Right> allRights) {
		this.allRights = allRights;
	}

	@Resource(name="rightService")
	private RightService rightService;
    
    public String toRightListPage(){
    	this.allRights=rightService.getAllEntities();
    	return "toRightListPage";
    }
    
    public String toAddRightPage(){
    	
    	return "toAddRightPage";
    }
    public String editRight(){
    	this.model=rightService.getEntity(rightId);
    	return "toAddRightPage";
    }
    public String saveOrUpdateRight(){
    	rightService.saveOrUpdateEntity(model);
    	return "toRightListPageAction";
    }
    public String deleteRight(){
    	Right right=new Right();
    	right.setId(rightId);
    	rightService.deleteEntity(right);
    	return "toRightListPageAction";
    }
    
    //批量更新权限
    public String batchUpdateRights(){
    	rightService.batchUpdateRights(allRights);
    	return "toRightListPageAction";
    }
}
