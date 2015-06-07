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
	 * �漰���ı�
	 *    jbpm4_participation
	 *        ������ĺ�ѡ�������ű���
	 *        Ҳ��һ����ʱ��
	 */
	@Test
	public void queryParticipation(){
		List<Participation> plist=processEngine.getTaskService()
		.getTaskParticipations("360002");
		for(Participation participation:plist){
			System.out.println(participation.getUserId());
		}
	}
	//���ݺ�ѡ�˲�ѯ������
	@Test
	public void queryTaskByUserId(){
		List<Task> list=processEngine.getTaskService()
		.findGroupTasks("����ʦ1");
		for(Task task:list){
			System.out.println(task.getName());
		}
	}
	/**
	 * ��������
	 *     jbpm��API���κ�һ���˶���ִ�и����񣬲��������ں�ѡ��
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
