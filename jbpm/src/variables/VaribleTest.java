package variables;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


import org.junit.Test;

import bean.Person;

import utils.BaseJbpm;

/**
 * ���̱���
 *    *  ���̱�������������
 *        ������ʵ����ʼ������ʵ������
 *        ���̱���������������ʵ����
 *    *  ��ô�������̱������뵽����ʵ����
 *        ����������ʵ����ʱ��
 *        ����������ʱ��
 *        ������ʵ����ʼ�Ժ����֮ǰ
 *    *  ��ô�������̱���������ʵ���л�ȡ��
 *        ������ʵ����ʼ�Ժ����֮ǰ�������Ի�ȡ�����̱���
 *    *  ʲô�������Ϳ�����Ϊ���̱���
 * @author Administrator
 *
 */
public class VaribleTest extends BaseJbpm{

	/**
	 * �漰���ı�
	 *    jbpm4_variable
	 *       ������̱���
	 *       ��һ������ʵ�������Ժ󣬹��ڸ�����ʵ�������̱���Ҳ��ɾ����
	 *     �ñ�Ҳ��һ����ʱ����ʱ������̱���
	 *     
	 *   *  jbpm4�ṩ�����̱����־û��ı��淽��
	 *   *  ���������jbpm4�ķ��������̱����־û�����ô����ζ�����е����ݵ����ݽṹд����
	 *   *  ���������Ҫ�Ƚϸ��ӵĲ�ѯͳ�ƣ�jbpm4����ı�������㲻������
	 *   *  ���̿��Ƶı�����ݽṹ�ı��Ƿֿ���
	 */
	
	@Test
	public void testWhenStartPI(){
		Person person=new Person();
		person.setId(1L);
		person.setName("����");
		Map<String,Object> varible=new HashMap<String,Object>();
		varible.put("����ʵ����ʼ",	"��ʼ��");
		varible.put("person", person);
		processEngine.getExecutionService()
		.startProcessInstanceById("qingjia1-2", varible);
	}
	
	@Test
	public void testWhenfinishTask(){
		Map<String,String> varible=new HashMap<String,String>();
		varible.put("�������",	"2");
		processEngine.getTaskService().setVariables("110002", varible);
//		processEngine.getTaskService().completeTask("150002");
		
	}
	
	@Test
	public void testShowVariblesByTask(){
		Set<String>set=processEngine.getTaskService()
		.getVariableNames("110002");
		for(String name:set){
			System.out.println(processEngine.getTaskService().getVariable("110002", name));
		}
	}
	
	@Test
	public void testByPiid(){
		Set<String> set=processEngine.getExecutionService()
		.getVariableNames("qingjia1.110001");
		System.out.println(set.size());
		Person person=(Person) processEngine.getExecutionService().getVariable("qingjia1.230001", "person");
	    System.out.println(person.getName());
	}
	@Test
	public void testWhenPI(){
		processEngine.getExecutionService().setVariable("qingjia1.110001", "����ʵ���еı���", "�� ˵�ľ�����");
	}
}
