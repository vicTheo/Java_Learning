package task;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import utils.BaseJbpm;

/**
 * 给任务的执行人动态的赋值
 *    *  在*.jpdl.xml文件中
 *        在task元素中
 *         <task name="部门经理审批" g="296,170,92,52">
 *          <assignment-handler class="cn.itcast.jbpm0909.task.MyAssignmentHandler">
 *          </assignment-handler>
 *         </task>
 *        可以在MyAssignmentHandler中给当前的任务赋值执行人，当进入当前节点时，assign方法执行
 *    *  在*.jpdl.xml文件中
 *         <task name="申请请假" g="142,103,92,52" assignee="#{applicator}">
 *         assignee可以通过流程变量的方式进行赋值
 *         在执行该任务节点之前，必须给applicator赋值
 *    *   可以通过taskService.assignTask(String taskId,String userId);给正在执行的任务赋值任务的执行人
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
		varible.put("applicator", "小王");
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
		//该API只能对当前的任务赋值
		processEngine.getTaskService()
		.assignTask("330001", "呵呵");
	}
}
