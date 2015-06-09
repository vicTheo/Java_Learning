package OAproject.Service;

import java.io.File;
import java.util.Collection;

import OAproject.Domain.Approval;
import OAproject.Domain.Form;
import OAproject.Domain.FormTemplate;
import OAproject.Domain.TaskView;

public interface WorkFlowService{
public Collection<FormTemplate> getAllFormTemplates();
public void submit(File resource,Long ftid);
public Collection<TaskView> getTaskViewByAsignee();
public Form getFormById(Long fid);
public void approve(String taskId,Approval approval);
}
