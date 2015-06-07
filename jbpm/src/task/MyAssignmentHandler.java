package task;

import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.task.Assignable;
import org.jbpm.api.task.AssignmentHandler;

@SuppressWarnings("serial")
public class MyAssignmentHandler implements AssignmentHandler{

	public void assign(Assignable assignable, OpenExecution execution) throws Exception {
		// TODO Auto-generated method stub
		String manager=(String) execution.getVariable("manager");
		assignable.setAssignee(manager);
	}

}
