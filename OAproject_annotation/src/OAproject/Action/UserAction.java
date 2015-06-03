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
		 * 1���Ѳ��ű�����е����ݲ�ѯ����
		 * 2���Ѹ�λ������ݲ�ѯ����
		 * 3����ת������ҳ��
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
		 * ��λ�ȡҳ���е�����
		 *    *  ���ҳ���е�������Դ��һ�ű�ֱ����ģ��������ȡ�Ϳ�����
		 *    *  ���ҳ���е�������Դ�ڶ��ű�����Բ���ģ������������������ķ�ʽ
		 */
		/**
		 * 1������һ��user����
		 * 2����ģ��������ֵ��ֵ��user����
		 * 3������ did��ȡ���ò���
		 * 4������pids��ȡ����λ
		 * 5������user����Ͳ��ź͸�λ֮��Ĺ�ϵ
		 * 6��ִ��save����
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
