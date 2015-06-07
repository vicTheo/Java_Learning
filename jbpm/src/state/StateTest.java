package state;

import org.junit.Test;

import utils.BaseJbpm;

public class StateTest extends BaseJbpm{

	@Test
	public void deploy(){
		processEngine.getRepositoryService()
		.createDeployment()
		.addResourceFromClasspath("state/state.jpdl.xml").deploy();
	}
	@Test
	public void startPI(){
		processEngine.getExecutionService()
		.startProcessInstanceById("state-1");
	}
	@Test
	public void passState(){
		String signalname="to end1";
		processEngine.getExecutionService()
		.signalExecutionById("state.400001", signalname);
	}
}
