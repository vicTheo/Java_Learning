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
		 *  *  �ϴ���
         *     ��form���в���һ������
                      applicatetime  ��ǰʱ��
                      applicator   ���ǵ���ϵͳ����
                      state        ������
                      url          �ϴ����Ժ�������ɸ�ֵ
                      ftid         ��ģ��ID(��ҳ����Ӧ����������)
         *     jbpm
             *  ��������ʵ��
                	����pdkey��������ʵ������Ϊҳ���ϴ��ݵ���̨�Ĳ���ֻ��pdkey
                	��form��Ϊ���̱��������浽����ʵ����
             *  ���������������
                completeTask(String taskId);
		 */
		//��ȡ��formtemplate
		FormTemplate formTemplate=this.formTemplateDao.getDEntryById(ftid);
		//����form��
		Form form=new Form();
		User applicator=OAUtils.getUserFromSession();
		form.setApplicator(applicator.getUsername());
		form.setApplicateTime(new Date());
		form.setState("������");
		form.setFormTemplate(formTemplate);
		String title=formTemplate.getName()+"-"+applicator.getUsername()+"-"+new Date();
		form.setTitle(title);
		String url=UploadUtils.saveUploadFile(resource);
		form.setUrl(url);
		this.workFlowDao.saveEntry(form);
		/*
		 * jbpm
		 */
		//��������ʵ���������̱���
		Map<String,Form> varibles=new HashMap<String, Form>();
		varibles.put("form", form);
		ProcessInstance processInstance=this.processEngine.getExecutionService()
		.startProcessInstanceByKey(formTemplate.getProcesskey(), varibles);
		
		//���ݵ�ǰ����ʵ����ȡ����ִ�е�����
		Task task=processEngine.getTaskService().createTaskQuery()
				.executionId(processInstance.getId()).uniqueResult();
	    //����������
	    this.processEngine.getTaskService().completeTask(task.getId());
	}
	public Collection<TaskView> getTaskViewByAsignee() {
		// TODO Auto-generated method stub
		/*
		 *����ִ���˻�ȡ����
		 *�������е����񣬵õ�executionid
		 *����executionid�����̱���form��ȡ����
		 *form �� task���taskview
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
		 * 1������һ�����ݵ�approve��
		 * 2�����ҳ�������ǲ�ͬ��
		 *      ������ʵ��ֱ�ӽ���
		 *      ����Ӧ��form���е�״̬���"δͨ��"
		 *   ���ҳ��������ͬ��
		 *      *  �������
		 *      *  �жϸ�����ʵ���Ƿ����
		 *          �������������Ӧ��form���״̬���"�����"
		 */
		Task task=this.processEngine.getTaskService().getTask(taskId);
		if(approval.getIsapproved().equals("ͬ��")){
			this.processEngine.getTaskService().completeTask(taskId);
			ProcessInstance pi=this.processEngine.getExecutionService().createProcessInstanceQuery()
					.processInstanceId(task.getExecutionId()).uniqueResult();
			if(pi==null){
				approval.getForm().setState("��ͨ��");
				
			}
		}else{
			this.processEngine.getExecutionService()
			.endProcessInstance(task.getExecutionId(), "ended");
			approval.getForm().setState("δͨ��");
		}
		this.workFlowDao.saveEntry(approval);
	}

	
}
