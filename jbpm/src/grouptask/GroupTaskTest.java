package grouptask;

import java.util.List;

import org.jbpm.api.task.Participation;
import org.jbpm.api.task.Task;
import org.junit.Test;

import utils.BaseJbpm;

public class GroupTaskTest extends BaseJbpm{
    
	@Test
	public void Deploy(){
		processEngine.getRepositoryService()
		.createDeployment().addResourceFromClasspath("grouptask/grouptask.jpdl.xml")
		.deploy();
		
	}
	
	@Test
	public void startPI(){
		processEngine.getExecutionService()
		.startProcessInstanceById("grouptask-1");
	} 
	/**
	 * 涉及到的表
	 *    jbpm4_participation
	 *        组任务的候选人在这张表中
	 *        也是一个临时表
	 */
	@Test
	public void queryParticipation(){
		List<Participation> plist=processEngine.getTaskService()
		.getTaskParticipations("360002");
		for(Participation participation:plist){
			System.out.println(participation.getUserId());
		}
	}
	//根据候选人查询组任务
	@Test
	public void queryTaskByUserId(){
		List<Task> list=processEngine.getTaskService()
		.findGroupTasks("工程师1");
		for(Task task:list){
			System.out.println(task.getName());
		}
	}
	/**
	 * 接受任务
	 *     jbpm的API：任何一个人都能执行该任务，并不局限于候选人
	 */
	@Test
	public void takeTask(){
		processEngine.getTaskService()
		.takeTask("360002", "aa");
	}
	@Test
	public void finishTask(){
		processEngine.getTaskService()
		.completeTask("360002");
	}
}
