package OAproject.Domain;

import org.jbpm.api.task.Task;

public class TaskView {
private Form form;
private Task task;
public Form getForm() {
	return form;
}
public void setForm(Form form) {
	this.form = form;
}
public Task getTask() {
	return task;
}
public void setTask(Task task) {
	this.task = task;
}

}
