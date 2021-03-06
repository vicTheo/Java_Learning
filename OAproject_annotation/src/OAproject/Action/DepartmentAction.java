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
	//把departmentList放入到值栈中
	/**
	 * 值栈
	 *   *  值栈的生命周期
	 *        值栈的生命周期就是一次请求
	 *   *  值栈的数据结构
	 *        对象栈
	 *        map栈
	 *   *  对象栈和map栈有什么区别
	 *        对象栈是一个list
	 *        map栈是一个map
	 *   *  怎么样把一个数据放入到map栈中
	 *   *  怎么样把一个数据放入到对象栈中
	 *   		
	 *   *  对象栈中的数据有什么样的特殊之处
	 */
	//把departmentList放入到了对象栈的栈顶
	//ActionContext.getContext().getValueStack().push(departmentList);
	//把departmentList放入到了对象栈的栈顶
	//ActionContext.getContext().getValueStack().getRoot().add(0, departmentList);
	//把departmentList放入到了对象栈的栈底
	//ActionContext.getContext().getValueStack().getRoot().add(departmentList);
	//获取对象栈的栈顶的元素
	//ActionContext.getContext().getValueStack().peek();
	//移除对象栈的栈顶的元素
	//ActionContext.getContext().getValueStack().pop();
	//移除对象栈的栈顶的元素
	//ActionContext.getContext().getValueStack().getRoot().remove(0);
	//把一个map放入到对象栈的栈顶
	//ActionContext.getContext().getValueStack().set(key, o);
	/**
	 * 对象栈的说明
	 *    *  处于对象栈的对象中的属性是可以直接访问的
	 *    *  如果在对象栈中有一样名称的属性，从栈顶开始查找，直到找到为止
	 *    *  一般情况下，回显的数据应该放在对象栈中，这样效果比较高
	 *    *  用ognl表达式访问对象栈，直接属性名称就可以了，不用加#
	 */
	
	//map栈
	/**
	 * 说明
	 *   *  reuqest,session,application都在map栈中
	 *   *  可以把一个对象放入到map中
	 *   *  ognl表达式访问map栈中的内容
	 *       如果一个对象在request中
	 *          #request.对象的key值.属性
	 *       如果一个对象直接放入到map中
	 *          #对象的key值.属性
	 *       把一个对象放入到map栈中，是不能直接访问该对象的属性
	 */
	//把一个对象存放到map栈中
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
	//根据传来的id从数据库或session中得到
	Department department=(Department) this.departmentService.getEntryById(this.getModel().getDid());
	
	//将对象放入对象栈栈顶  页面可直接通过 name属性回显
	ActionContext.getContext().getValueStack().getRoot().add(0,department);
	
//	//将对象放入map中 这样页面要通过 value="%{ognl表达式}" 来回显
	ActionContext.getContext().put("department", department);
	return updateUI;
}
public String add(){
	/*创建department对象
	 * 将model中的值赋给department
	 * 传给service层执行增操作
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
