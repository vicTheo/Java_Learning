package transition;

import org.junit.Test;

import utils.BaseJbpm;

public class TransitionTest extends BaseJbpm{

@Test
public void deploy(){
	processEngine.getRepositoryService()
	.createDeployment()
	.addResourceFromClasspath("transition/transition.jpdl.xml")
	.deploy();
}

@Test
public void startPI(){
	processEngine.getExecutionService()
	.startProcessInstanceById("transition-1");
}
@Test
public void finishTask(){
	//outcomeָ��transition������
	String outcome="to end1";
	processEngine.getTaskService()
	.completeTask("380002", outcome);
}
}
