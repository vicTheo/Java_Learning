package task;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import utils.BaseJbpm;

/**
 * �������ִ���˶�̬�ĸ�ֵ
 *    *  ��*.jpdl.xml�ļ���
 *        ��taskԪ����
 *         <task name="���ž�������" g="296,170,92,52">
 *          <assignment-handler class="cn.itcast.jbpm0909.task.MyAssignmentHandler">
 *          </assignment-handler>
 *         </task>
 *        ������MyAssignmentHandler�и���ǰ������ִֵ���ˣ������뵱ǰ�ڵ�ʱ��assign����ִ��
 *    *  ��*.jpdl.xml�ļ���
 *         <task name="�������" g="142,103,92,52" assignee="#{applicator}">
 *         assignee����ͨ�����̱����ķ�ʽ���и�ֵ
 *         ��ִ�и�����ڵ�֮ǰ�������applicator��ֵ
 *    *   ����ͨ��taskService.assignTask(String taskId,String userId);������ִ�е�����ֵ�����ִ����
 * @author Administrator
 *
 */
public class TaskTest extends  BaseJbpm{

	@Test
	public void testDeploy(){
		processEngine.getRepositoryService()
		.createDeployment()
		.addResourceFromClasspath("task/task.jpdl.xml")
		.addResourceFromClasspath("task/task.png")
		.deploy();
	}
	@Test
	public void testStartPi(){
		Map<String,String> varible=new HashMap<String, String>();
		varible.put("applicator", "С��");
		varible.put("manager", "xxxx");
		processEngine.getExecutionService()
		.startProcessInstanceById("task-1",varible);
	}
	
	@Test
	public void testCompleteTask(){
		processEngine.getTaskService()
		.completeTask("320004");
	}
	@Test
	public void testAssigneeWhenTask(){
		//��APIֻ�ܶԵ�ǰ������ֵ
		processEngine.getTaskService()
		.assignTask("330001", "�Ǻ�");
	}
}
