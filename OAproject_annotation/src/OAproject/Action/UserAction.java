package OAproject.Action;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;


import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;


import OAproject.Action.Base.BaseAction;
import OAproject.Domain.Department;
import OAproject.Domain.Post;
import OAproject.Domain.User;
import OAproject.Service.DepartmentService;
import OAproject.Service.PostService;
import OAproject.Service.UserService;
@Controller("userAction")
@Scope("prototype")
public class UserAction extends BaseAction<User> {
    @Resource(name="userService")
	private UserService userService; 
    @Resource(name="departmentService")
    private DepartmentService departmentSevice;
    @Resource(name="postService")
    private PostService postService;
    private Long did;
    private Long[] pids;
    
    public Long getDid() {
		return did;
	}

	public void setDid(Long did) {
		this.did = did;
	}

	public Long[] getPids() {
		return pids;
	}

	public void setPids(Long[] pids) {
		this.pids = pids;
	}

	public String getAllUsers(){
    	Collection<User> userList=this.userService.getAllUsers();
    	/*for(User user:userList){
    		System.out.println(user.getUid());
    	}*/
        ActionContext.getContext().getValueStack().push(userList);
        return listAction;
    }
    
    public String addUI(){
    	/**
		 * 1、把部门表的所有的数据查询出来
		 * 2、把岗位表的数据查询出来
		 * 3、跳转到增加页面
		 */
    	Collection<Department> depList=this.departmentSevice.getAllEntry();
    	Collection<Post> postList=this.postService.getAllEntry();
    	System.out.println(postList.size());
    	ActionContext.getContext().put("postList", postList);
    	ActionContext.getContext().put("deptList", depList);
    	return addUI;
    }
    
    public String add(){
    	/**
		 * 如何获取页面中的数据
		 *    *  如果页面中的数据来源于一张表，直接用模型驱动获取就可以了
		 *    *  如果页面中的数据来源于多张表，则可以采用模型驱动结合属性驱动的方式
		 */
		/**
		 * 1、创建一个user对象
		 * 2、把模型驱动的值赋值给user对象
		 * 3、根据 did提取出该部门
		 * 4、根据pids提取出岗位
		 * 5、建立user对象和部门和岗位之间的关系
		 * 6、执行save操作
		 */
    	User user=new User();
    	BeanUtils.copyProperties(this.getModel(), user);
    	Department department=(Department) this.departmentSevice.getEntryById(this.getDid());
    	user.setDepartment(department);
    	Set<Post> postList=(Set<Post>) this.postService.getAllPostsByIds(pids);
    	user.setPosts(postList);
    	return action2action;
    }
}
