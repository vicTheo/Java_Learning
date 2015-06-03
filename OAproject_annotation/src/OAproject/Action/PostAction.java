package OAproject.Action;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import OAproject.Action.Base.BaseAction;

import OAproject.Domain.Post;
import OAproject.Service.PostService;


@Controller("postAction")
@Scope("prototype")
public class PostAction extends BaseAction<Post>{
   
	   @Resource(name="postService")
		private PostService postService;
	   

	public String getAllPost(){
		Collection<Post> postList = this.postService.getAllEntry();
	    ActionContext.getContext().put("postList", postList);
		return listAction;
	}


	public String deletePostById(){
		this.postService.deleteEntryById(this.getModel().getPid());
		return action2action;
	}

	public String addUI(){
		
		return addUI;
	}
	public String updateUI() {
		//根据传来的id从数据库或session中得到
		Post post=(Post) this.postService.getEntryById(this.getModel().getPid());
		
		//将对象放入对象栈栈顶  页面可直接通过 name属性回显
		ActionContext.getContext().getValueStack().getRoot().add(0,post);
		
         
		return updateUI;
	}
	public String add(){
		/*创建post对象
		 * 将model中的值赋给post
		 * 传给service层执行增操作
		 */
		Post post=new Post();
		BeanUtils.copyProperties(this.getModel(), post);
		System.out.println(this.getModel().getDescription());
		this.postService.saveEntry(post);
		return action2action;
	}
	public String update(){
		Post post=new Post();
		BeanUtils.copyProperties(this.getModel(), post);
		this.postService.updateEntry(post);
		return action2action;
	}
}
