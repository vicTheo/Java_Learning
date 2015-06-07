package event;

import org.junit.Test;

import utils.BaseJbpm;




/**
 * event��decision�ķ�������Ϊ�˴ﵽ����������
 *   �����̵Ŀ����߼������̵�ҵ���߼�����
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
