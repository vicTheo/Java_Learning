package pi;

import java.util.List;

import org.jbpm.api.ProcessInstance;
import org.jbpm.api.history.HistoryTask;
import org.jbpm.api.task.Task;
import org.junit.Test;

import utils.BaseJbpm;
/**
 * 流程实例
 *    *  启动流程实例
 *    *  完成任务
 *    *  查询
 *       *  查询流程实例
 *       *  查询任务
 *          *  查询正在执行的任务
 *             *  查询所有的正在执行的任务
 *             *  根据任务的执行人查询正在执行的任务
 *             *  根据executionId查询正在执行的任务
 *             *  根据piid查询正在执行的任务
 *             *  根据pdid查询正在执行的任务
 *             *  根据taskid查询任务  taskService.getTask(String taskId);和其他的查询不一样
 *          *  查询已经完成的任务
 *             *  查询所有的
 *             *  根据任务的执行人
 *             *  根据executionID
 *             *  .......
 *    *  结束流程实例
 * @author Administrator
 *
 */
public class ProcessInstanceTest  extends BaseJbpm{
	/**
	 * 涉及到的表
	 *    *  JBPM4_EXECUTION
	 *        *  表示当前正在执行的流程实例
	 *        *  字段 
	 *            DBID_:主键
	 *            ID_:流程实例ID
	 *            ACTIVITYNAME_:当前流程实例活跃的节点
	 *            PROCDEFID_:流程定义ID
	 *    *  JBPM4_HIST_PROCINST
	 *        *  表示历史的流程实例，但是当前正在运行的流程实例也在这里
	 *        *  字段
	 *            DBID_:主键
	 *            ID_:流程实例ID
	 *            START_:整个流程实例的开始时间
	 *            END_:流程实例的结束时间，如果该流程实例为当前正在运行的流程则，该值为空
	 *            STATE:表示流程实例的状态  如果是正在运行，为active  如果流程实例结束  为ended
	 *    *  JBPM4_TASK
	 *        *  表示当前正在执行的任务
	 *          说明：任务是jbpm流程图的一个节点
	 *        *  字段
	 *           DBID_:主键、任务ID
	 *           NAME_:任务名称
	 *           ASSIGNEE_:任务的执行人
	 *    *  JBPM4_HIST_TASK
	 *        *  表示历史的任务，但是当前正在执行的任务也在这里
	 *        *  字段
	 *          	STATE_:如果一个任务被完成以后，值为completed
	 *              end_:任务的结束时间有值了
	 *    *  JBPM4_HIST_ACTINST
	 *        *  表示历史的节点
	 *        *  字段
	 *            TYPE_:节点类型
	 *  说明：
	 *     *  当启动流程实例的时候，会自动的离开开始节点，流向下一个节点
	 *     *  jbpm4_task为临时表，当当前的任务完成以后，该数据就被删除了
	 */
	
	@Test
	public void testStartPIbyId(){
	          ProcessInstance processInstance= processEngine.getExecutionService().startProcessInstanceById("qingjia1-1");
		      System.out.println(processInstance.getState());
		      System.out.println(processInstance.getId());
	}
	
	/**
	 * 根据pdkey启动流程实例，是该key下，最高版本
	 */
	@Test
	public void testStartPIByKey(){
		  processEngine.getExecutionService().startProcessInstanceByKey("qingjia1");
	}
	/*
	 * 完成任务
	 */
	@Test
	public void testCompleteTask(){
		processEngine.getTaskService().completeTask("80002");
	}
	/*
	 * 查询所有的流程实例
	 */
	@Test
	public void testPIQuery(){
		List<ProcessInstance> list=processEngine.getExecutionService()
		.createProcessInstanceQuery().processDefinitionId("qingjia1-1") //可以根据流程定义id查询 对应多个实例
		.list();
	    for(ProcessInstance processInstance:list){
	    	 System.out.println(processInstance.getId());
	    	 System.out.println(processInstance.getState());
	    }
	}
	/*
	 * 查询所有正在执行的任务
	 */
	@Test
	public void testTaskQuery(){
		List<Task> taskList= processEngine.getTaskService().createTaskQuery().list();
		for(Task task:taskList){
			System.out.println(task.getAssignee()+","+task.getActivityName()+","+task.getId());
		}
	}
	/**
	 * 可以根据任务的执行人查询当前正在执行的任务
	 * 可以根据pdid查询当前正在执行的任务
	 */
	@Test
	public void testTaskQuery1(){
		   List<Task> taskList=processEngine.getTaskService()
		   .createTaskQuery()
//		   .assignee("李四")
		   .processDefinitionId("qingjia1-1")
		   .list();
			for(Task task:taskList){
				System.out.println(task.getAssignee()+","+task.getActivityName()+","+task.getId());
			}
	}
	/**
	 * Execution
	 *    *  如果不存在并发的情况，那么exection和process instance是一样的
	 *    *  如果存在并发的情况，execution代表分支，process instance代表主线
	 *    *  根据executionID查询任务，有唯一的一个
	 */
	@Test
	public void testTaskQueryById(){
		Task task=processEngine.getTaskService()
		.getTask("90002");
		System.out.println(task.getActivityName());
	}
	
	//获得完成的任务
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
	//直接结束流程实例
	@Test
	public void testEndPI(){
		processEngine.getExecutionService().endProcessInstance("qingjia1.80001", "error");
	}
	
	//根据piid查询流程实例 如果返回null则该流程实例已经结束
@Test
public void testIsEndPI(){
   ProcessInstance  p= processEngine.getExecutionService()
   .createProcessInstanceQuery()
   .processInstanceId("qingjia1.80001")
   .uniqueResult();
   System.out.println(p);
}
}