package OAproject.ServiceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import OAproject.Dao.FormTemplateDao;
import OAproject.Dao.WorkFlowDao;
import OAproject.Domain.Approval;
import OAproject.Domain.Form;
import OAproject.Domain.FormTemplate;
import OAproject.Domain.TaskView;
import OAproject.Domain.User;
import OAproject.Service.WorkFlowService;
import OAproject.util.OAUtils;
import OAproject.util.UploadUtils;
@Service("workflowService")
public class WorkFlowServiceImpl implements WorkFlowService{
    @Resource(name="formTemplateDao")
	private FormTemplateDao formTemplateDao;
    @Resource(name="workflowDao")
    private WorkFlowDao workFlowDao;
    @Resource(name="processEngine")
    private ProcessEngine processEngine;
	public Collection<FormTemplate> getAllFormTemplates() {
		// TODO Auto-generated method stub
		
		return this.formTemplateDao.getAllEntry();
	}
	@Transactional(readOnly=false)
	public void submit(File resource, Long ftid) {
		// TODO Auto-generated method stub
		/**
		 *  *  上传表单
         *     往form表中插入一行数据
                      applicatetime  当前时间
                      applicator   就是登入系统的人
                      state        审批中
                      url          上传表单以后可以生成该值
                      ftid         表单模板ID(在页面上应该用隐藏域)
         *     jbpm
             *  启动流程实例
                	根据pdkey启动流程实例，因为页面上传递到后台的参数只有pdkey
                	把form作为流程变量，保存到流程实例中
             *  完成请假申请的任务
                completeTask(String taskId);
		 */
		//获取到formtemplate
		FormTemplate formTemplate=this.formTemplateDao.getDEntryById(ftid);
		//保存form表单
		Form form=new Form();
		User applicator=OAUtils.getUserFromSession();
		form.setApplicator(applicator.getUsername());
		form.setApplicateTime(new Date());
		form.setState("申请中");
		form.setFormTemplate(formTemplate);
		String title=formTemplate.getName()+"-"+applicator.getUsername()+"-"+new Date();
		form.setTitle(title);
		String url=UploadUtils.saveUploadFile(resource);
		form.setUrl(url);
		this.workFlowDao.saveEntry(form);
		/*
		 * jbpm
		 */
		//启动流程实例设置流程变量
		Map<String,Form> varibles=new HashMap<String, Form>();
		varibles.put("form", form);
		ProcessInstance processInstance=this.processEngine.getExecutionService()
		.startProcessInstanceByKey(formTemplate.getProcesskey(), varibles);
		
		//根据当前流程实例获取正在执行的任务
		Task task=processEngine.getTaskService().createTaskQuery()
				.executionId(processInstance.getId()).uniqueResult();
	    //完成请假任务
	    this.processEngine.getTaskService().completeTask(task.getId());
	}
	public Collection<TaskView> getTaskViewByAsignee() {
		// TODO Auto-generated method stub
		/*
		 *根据执行人获取任务
		 *遍历所有的任务，得到executionid
		 *根据executionid把流程变量form提取出来
		 *form 和 task组成taskview
		 */
		List<TaskView> taskViewList=new ArrayList<TaskView>();
		List<Task> taskList=this.processEngine
				.getTaskService()
				.createTaskQuery()
				.assignee(OAUtils.getUserFromSession().getUsername())
				.list();
		for(Task task:taskList){
			TaskView taskView=new TaskView();
			Form form=(Form) this.processEngine.getExecutionService()
			.getVariable(task.getExecutionId(), "form");
			taskView.setForm(form);
			taskView.setTask(task);
			taskViewList.add(taskView);
		}
		return taskViewList;
	}
	public Form getFormById(Long fid) {
		// TODO Auto-generated method stub
		return (Form) this.workFlowDao.getDEntryById(fid);
	}
	@Transactional(readOnly=false)
	public void approve(String taskId,Approval approval) {
		// TODO Auto-generated method stub
		/**
		 * 1、插入一行数据到approve中
		 * 2、如果页面点击的是不同意
		 *      则流程实例直接结束
		 *      把相应的form表中的状态变成"未通过"
		 *   如果页面点击的是同意
		 *      *  完成任务
		 *      *  判断该流程实例是否结束
		 *          如果结束，把相应的form表的状态变成"已完成"
		 */
		Task task=this.processEngine.getTaskService().getTask(taskId);
		if(approval.getIsapproved().equals("同意")){
			this.processEngine.getTaskService().completeTask(taskId);
			ProcessInstance pi=this.processEngine.getExecutionService().createProcessInstanceQuery()
					.processInstanceId(task.getExecutionId()).uniqueResult();
			if(pi==null){
				approval.getForm().setState("已通过");
				
			}
		}else{
			this.processEngine.getExecutionService()
			.endProcessInstance(task.getExecutionId(), "ended");
			approval.getForm().setState("未通过");
		}
		this.workFlowDao.saveEntry(approval);
	}

	
}
