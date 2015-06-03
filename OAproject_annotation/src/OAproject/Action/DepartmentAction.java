package OAproject.Action;

import java.util.Collection;


import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.opensymphony.xwork2.ActionContext;
import OAproject.Action.Base.BaseAction;
import OAproject.Domain.Department;
import OAproject.Service.DepartmentService;

@Controller("departmentAction")
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department>{
   @Resource(name="departmentService")
	private DepartmentService departmentService;
   

public String getAllDepartment(){
	Collection<Department> departmentList = this.departmentService.getAllEntry();
	//��departmentList���뵽ֵջ��
	/**
	 * ֵջ
	 *   *  ֵջ����������
	 *        ֵջ���������ھ���һ������
	 *   *  ֵջ�����ݽṹ
	 *        ����ջ
	 *        mapջ
	 *   *  ����ջ��mapջ��ʲô����
	 *        ����ջ��һ��list
	 *        mapջ��һ��map
	 *   *  ��ô����һ�����ݷ��뵽mapջ��
	 *   *  ��ô����һ�����ݷ��뵽����ջ��
	 *   		
	 *   *  ����ջ�е�������ʲô��������֮��
	 */
	//��departmentList���뵽�˶���ջ��ջ��
	//ActionContext.getContext().getValueStack().push(departmentList);
	//��departmentList���뵽�˶���ջ��ջ��
	//ActionContext.getContext().getValueStack().getRoot().add(0, departmentList);
	//��departmentList���뵽�˶���ջ��ջ��
	//ActionContext.getContext().getValueStack().getRoot().add(departmentList);
	//��ȡ����ջ��ջ����Ԫ��
	//ActionContext.getContext().getValueStack().peek();
	//�Ƴ�����ջ��ջ����Ԫ��
	//ActionContext.getContext().getValueStack().pop();
	//�Ƴ�����ջ��ջ����Ԫ��
	//ActionContext.getContext().getValueStack().getRoot().remove(0);
	//��һ��map���뵽����ջ��ջ��
	//ActionContext.getContext().getValueStack().set(key, o);
	/**
	 * ����ջ��˵��
	 *    *  ���ڶ���ջ�Ķ����е������ǿ���ֱ�ӷ��ʵ�
	 *    *  ����ڶ���ջ����һ�����Ƶ����ԣ���ջ����ʼ���ң�ֱ���ҵ�Ϊֹ
	 *    *  һ������£����Ե�����Ӧ�÷��ڶ���ջ�У�����Ч���Ƚϸ�
	 *    *  ��ognl���ʽ���ʶ���ջ��ֱ���������ƾͿ����ˣ����ü�#
	 */
	
	//mapջ
	/**
	 * ˵��
	 *   *  reuqest,session,application����mapջ��
	 *   *  ���԰�һ��������뵽map��
	 *   *  ognl���ʽ����mapջ�е�����
	 *       ���һ��������request��
	 *          #request.�����keyֵ.����
	 *       ���һ������ֱ�ӷ��뵽map��
	 *          #�����keyֵ.����
	 *       ��һ��������뵽mapջ�У��ǲ���ֱ�ӷ��ʸö��������
	 */
	//��һ�������ŵ�mapջ��
	ActionContext.getContext().put("departmentList", departmentList);
	//#request.deparmentList
	//ServletActionContext.getRequest().setAttribute("departmentList", departmentList);
//	List<List<Department>> lists = new ArrayList<List<Department>>();
//	Department department1 = new  Department();
//	department1.setDname("department1_name");
//	Department department2 = new  Department();
//	department2.setDname("department2_name");
//	List<Department> departmentList1 = new ArrayList<Department>();
//	departmentList1.add(department1);
//	List<Department> departmentList2 = new ArrayList<Department>();
//	departmentList2.add(department2);
//	lists.add(departmentList1);
//	lists.add(departmentList2);
//	
//	List<Map<String, Department>> lists2 = new ArrayList<Map<String,Department>>();
//	Map<String, Department> map1 = new HashMap<String, Department>();
//	map1.put("d1", department1);
//	Map<String, Department> map2 = new HashMap<String, Department>();
//	map2.put("d2", department2);
//	lists2.add(map1);
//	lists2.add(map2);
//	
//	Map<String, List<Department>> maps = new HashMap<String, List<Department>>();
//	maps.put("list1", departmentList1);
//	maps.put("list2", departmentList2);
  //ActionContext.getContext().put("maps", maps);
	
	return listAction;
}


public String deleteDepById(){
	this.departmentService.deleteEntryById(this.getModel().getDid());
	return action2action;
}

public String addUI(){
	
	return addUI;
}
public String updateUI() {
	//���ݴ�����id�����ݿ��session�еõ�
	Department department=(Department) this.departmentService.getEntryById(this.getModel().getDid());
	
	//������������ջջ��  ҳ���ֱ��ͨ�� name���Ի���
	ActionContext.getContext().getValueStack().getRoot().add(0,department);
	
//	//���������map�� ����ҳ��Ҫͨ�� value="%{ognl���ʽ}" ������
	ActionContext.getContext().put("department", department);
	return updateUI;
}
public String add(){
	/*����department����
	 * ��model�е�ֵ����department
	 * ����service��ִ��������
	 */
	Department department=new Department();
	BeanUtils.copyProperties(this.getModel(), department);
	System.out.println(this.getModel().getDescription());
	this.departmentService.saveEntry(department);
	return action2action;
}
public String update(){
	Department department=new Department();
	BeanUtils.copyProperties(this.getModel(), department);
	this.departmentService.updateEntry(department);
	return action2action;
}
}
