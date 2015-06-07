package decision;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

@SuppressWarnings("serial")
public class MyHandler implements DecisionHandler{

	public String decide(OpenExecution execution) {
		// TODO Auto-generated method stub
		int days=(Integer) execution.getVariable("days");
		if(days>3){
			return "to task3";
		}else{
			return "to end1";
		}
	}

}
