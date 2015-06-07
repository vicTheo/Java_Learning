package decision;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import utils.BaseJbpm;

public class DecisionTest extends BaseJbpm{

	@Test
	public void deploy(){
		processEngine.getRepositoryService()
		.createDeployment().addResourceFromClasspath("decision/decision.jpdl.xml")
		.deploy();
	}
	@Test
	public void startPI(){
		processEngine.getExecutionService()
		.startProcessInstanceById("decision-2");
	}
	@Test
	public void finishTask1(){
		
		processEngine.getTaskService()
		.completeTask("540002");
	}
	@Test
	public void finishTask2(){
		Map<String,Integer> varible=new HashMap<String, Integer>();
		varible.put("days", 4);
		processEngine.getTaskService().setVariables("550001", varible);
		processEngine.getTaskService().completeTask("550001");
	}
}
