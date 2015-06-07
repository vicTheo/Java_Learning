package variables;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


import org.junit.Test;

import bean.Person;

import utils.BaseJbpm;

/**
 * 流程变量
 *    *  流程变量的生命周期
 *        从流程实例开始到流程实例结束
 *        流程变量是依附于流程实例的
 *    *  怎么样把流程变量放入到流程实例中
 *        在启动流程实例的时候
 *        在完成任务的时候
 *        在流程实例开始以后结束之前
 *    *  怎么样把流程变量从流程实例中获取到
 *        在流程实例开始以后结束之前，都可以获取到流程变量
 *    *  什么样的类型可以作为流程变量
 * @author Administrator
 *
 */
public class VaribleTest extends BaseJbpm{

	/**
	 * 涉及到的表
	 *    jbpm4_variable
	 *       存放流程变量
	 *       当一个流程实例结束以后，关于该流程实例的流程变量也被删除了
	 *     该表也是一个临时表，临时存放流程变量
	 *     
	 *   *  jbpm4提供了流程变量持久化的保存方法
	 *   *  但是如果用jbpm4的方法把流程变量持久化，那么就意味着所有的数据的数据结构写死了
	 *   *  如果将来需要比较复杂的查询统计，jbpm4本身的表根本满足不了需求
	 *   *  流程控制的表和数据结构的表是分开的
	 */
	
	@Test
	public void testWhenStartPI(){
		Person person=new Person();
		person.setId(1L);
		person.setName("李雷");
		Map<String,Object> varible=new HashMap<String,Object>();
		varible.put("流程实例开始",	"开始了");
		varible.put("person", person);
		processEngine.getExecutionService()
		.startProcessInstanceById("qingjia1-2", varible);
	}
	
	@Test
	public void testWhenfinishTask(){
		Map<String,String> varible=new HashMap<String,String>();
		varible.put("请假天数",	"2");
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
		processEngine.getExecutionService().setVariable("qingjia1.110001", "流程实例中的变量", "对 说的就是我");
	}
}
