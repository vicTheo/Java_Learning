package event;

import org.junit.Test;

import utils.BaseJbpm;




/**
 * event和decision的发明就是为了达到代码的松耦合
 *   把流程的控制逻辑和流程的业务逻辑分离
 * @author Administrator
 *
 */
public class EventTest extends BaseJbpm{
	@Test
	public void testDeploy(){
		processEngine.getRepositoryService()
		.createDeployment()
		.addResourceFromClasspath("event/event.jpdl.xml")
		.deploy();
	}
	
	@Test
	public void testStartPI(){
		processEngine.getExecutionService()
		.startProcessInstanceById("event-2");
	}
	
	@Test
	public void testFinishTask(){
		processEngine.getTaskService()
		.completeTask("610002");
	}
}
