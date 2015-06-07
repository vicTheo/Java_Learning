package pi;

import java.util.List;

import org.jbpm.api.ProcessInstance;
import org.jbpm.api.history.HistoryTask;
import org.jbpm.api.task.Task;
import org.junit.Test;

import utils.BaseJbpm;
/**
 * ����ʵ��
 *    *  ��������ʵ��
 *    *  �������
 *    *  ��ѯ
 *       *  ��ѯ����ʵ��
 *       *  ��ѯ����
 *          *  ��ѯ����ִ�е�����
 *             *  ��ѯ���е�����ִ�е�����
 *             *  ���������ִ���˲�ѯ����ִ�е�����
 *             *  ����executionId��ѯ����ִ�е�����
 *             *  ����piid��ѯ����ִ�е�����
 *             *  ����pdid��ѯ����ִ�е�����
 *             *  ����taskid��ѯ����  taskService.getTask(String taskId);�������Ĳ�ѯ��һ��
 *          *  ��ѯ�Ѿ���ɵ�����
 *             *  ��ѯ���е�
 *             *  ���������ִ����
 *             *  ����executionID
 *             *  .......
 *    *  ��������ʵ��
 * @author Administrator
 *
 */
public class ProcessInstanceTest  extends BaseJbpm{
	/**
	 * �漰���ı�
	 *    *  JBPM4_EXECUTION
	 *        *  ��ʾ��ǰ����ִ�е�����ʵ��
	 *        *  �ֶ� 
	 *            DBID_:����
	 *            ID_:����ʵ��ID
	 *            ACTIVITYNAME_:��ǰ����ʵ����Ծ�Ľڵ�
	 *            PROCDEFID_:���̶���ID
	 *    *  JBPM4_HIST_PROCINST
	 *        *  ��ʾ��ʷ������ʵ�������ǵ�ǰ�������е�����ʵ��Ҳ������
	 *        *  �ֶ�
	 *            DBID_:����
	 *            ID_:����ʵ��ID
	 *            START_:��������ʵ���Ŀ�ʼʱ��
	 *            END_:����ʵ���Ľ���ʱ�䣬���������ʵ��Ϊ��ǰ�������е������򣬸�ֵΪ��
	 *            STATE:��ʾ����ʵ����״̬  ������������У�Ϊactive  �������ʵ������  Ϊended
	 *    *  JBPM4_TASK
	 *        *  ��ʾ��ǰ����ִ�е�����
	 *          ˵����������jbpm����ͼ��һ���ڵ�
	 *        *  �ֶ�
	 *           DBID_:����������ID
	 *           NAME_:��������
	 *           ASSIGNEE_:�����ִ����
	 *    *  JBPM4_HIST_TASK
	 *        *  ��ʾ��ʷ�����񣬵��ǵ�ǰ����ִ�е�����Ҳ������
	 *        *  �ֶ�
	 *          	STATE_:���һ����������Ժ�ֵΪcompleted
	 *              end_:����Ľ���ʱ����ֵ��
	 *    *  JBPM4_HIST_ACTINST
	 *        *  ��ʾ��ʷ�Ľڵ�
	 *        *  �ֶ�
	 *            TYPE_:�ڵ�����
	 *  ˵����
	 *     *  ����������ʵ����ʱ�򣬻��Զ����뿪��ʼ�ڵ㣬������һ���ڵ�
	 *     *  jbpm4_taskΪ��ʱ������ǰ����������Ժ󣬸����ݾͱ�ɾ����
	 */
	
	@Test
	public void testStartPIbyId(){
	          ProcessInstance processInstance= processEngine.getExecutionService().startProcessInstanceById("qingjia1-1");
		      System.out.println(processInstance.getState());
		      System.out.println(processInstance.getId());
	}
	
	/**
	 * ����pdkey��������ʵ�����Ǹ�key�£���߰汾
	 */
	@Test
	public void testStartPIByKey(){
		  processEngine.getExecutionService().startProcessInstanceByKey("qingjia1");
	}
	/*
	 * �������
	 */
	@Test
	public void testCompleteTask(){
		processEngine.getTaskService().completeTask("80002");
	}
	/*
	 * ��ѯ���е�����ʵ��
	 */
	@Test
	public void testPIQuery(){
		List<ProcessInstance> list=processEngine.getExecutionService()
		.createProcessInstanceQuery().processDefinitionId("qingjia1-1") //���Ը������̶���id��ѯ ��Ӧ���ʵ��
		.list();
	    for(ProcessInstance processInstance:list){
	    	 System.out.println(processInstance.getId());
	    	 System.out.println(processInstance.getState());
	    }
	}
	/*
	 * ��ѯ��������ִ�е�����
	 */
	@Test
	public void testTaskQuery(){
		List<Task> taskList= processEngine.getTaskService().createTaskQuery().list();
		for(Task task:taskList){
			System.out.println(task.getAssignee()+","+task.getActivityName()+","+task.getId());
		}
	}
	/**
	 * ���Ը��������ִ���˲�ѯ��ǰ����ִ�е�����
	 * ���Ը���pdid��ѯ��ǰ����ִ�е�����
	 */
	@Test
	public void testTaskQuery1(){
		   List<Task> taskList=processEngine.getTaskService()
		   .createTaskQuery()
//		   .assignee("����")
		   .processDefinitionId("qingjia1-1")
		   .list();
			for(Task task:taskList){
				System.out.println(task.getAssignee()+","+task.getActivityName()+","+task.getId());
			}
	}
	/**
	 * Execution
	 *    *  ��������ڲ������������ôexection��process instance��һ����
	 *    *  ������ڲ����������execution�����֧��process instance��������
	 *    *  ����executionID��ѯ������Ψһ��һ��
	 */
	@Test
	public void testTaskQueryById(){
		Task task=processEngine.getTaskService()
		.getTask("90002");
		System.out.println(task.getActivityName());
	}
	
	//�����ɵ�����
	@Test
	public void testGetCompletedTasj(){
	     List<HistoryTask> taskList=processEngine.getHistoryService()
		.createHistoryTaskQuery()
		.state("completed")
//		.executionId("")
//		.assignee("")
		.list();
	 	for(HistoryTask task:taskList){
			System.out.println(task.getAssignee()+","+task.getState()+","+task.getId());
		}
	}
	//ֱ�ӽ�������ʵ��
	@Test
	public void testEndPI(){
		processEngine.getExecutionService().endProcessInstance("qingjia1.80001", "error");
	}
	
	//����piid��ѯ����ʵ�� �������null�������ʵ���Ѿ�����
@Test
public void testIsEndPI(){
   ProcessInstance  p= processEngine.getExecutionService()
   .createProcessInstanceQuery()
   .processInstanceId("qingjia1.80001")
   .uniqueResult();
   System.out.println(p);
}
}