package OAproject.Action;

import java.io.File;
import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import OAproject.Action.Base.BaseAction;
import OAproject.Domain.Approval;
import OAproject.Domain.Form;
import OAproject.Domain.FormTemplate;
import OAproject.Domain.TaskView;
import OAproject.Service.WorkFlowService;
import OAproject.util.OAUtils;

@Controller("workflowAction")
@Scope("prototype")
public class WorkFlowAction extends BaseAction<Approval>{
   @Resource(name="workflowService")
   private WorkFlowService workFlowService;
   private File resource;
   private Long ftid;
   private Long fid;
   private String taskId;
   
   public Long getFid() {
	return fid;
}

public void setFid(Long fid) {
	this.fid = fid;
}

public String getTaskId() {
	return taskId;
}

public void setTaskId(String taskId) {
	this.taskId = taskId;
}

public File getResource() {
	return resource;
}

public void setResource(File resource) {
	this.resource = resource;
}

public Long getFtid() {
	return ftid;
}

public void setFtid(Long ftid) {
	this.ftid = ftid;
}

public String getAllFormTemplates(){
	   Collection<FormTemplate> ftList=this.workFlowService.getAllFormTemplates();
	   ActionContext.getContext().put("ftList", ftList);
	   return "workflow_formtemplate";
   }
   
   public String submitUI(){
	   return "submitUI";
   }
   public String submit(){
	   this.workFlowService.submit(resource, ftid);
	   return null;
   }
   
   public String myTaskViewList(){
	   Collection<TaskView> taskViewList=this.workFlowService.getTaskViewByAsignee();
	   ActionContext.getContext().put("taskViewList", taskViewList);
	   return "mytasklist";
   }
   public String approveUI(){
	   return "approveUI";
   }
   public String approve(){
	   Approval approval=new Approval();
	   BeanUtils.copyProperties(this.getModel(),approval);
	  Form form=this.workFlowService.getFormById(this.fid);
	  approval.setForm(form);
	  approval.setApprovetime(new Date());
	  approval.setApprovename(OAUtils.getUserFromSession().getUsername());
	  this.workFlowService.approve(this.taskId,approval);
	   return null;
   }
}
