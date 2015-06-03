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
		//���ݴ�����id�����ݿ��session�еõ�
		Post post=(Post) this.postService.getEntryById(this.getModel().getPid());
		
		//������������ջջ��  ҳ���ֱ��ͨ�� name���Ի���
		ActionContext.getContext().getValueStack().getRoot().add(0,post);
		
         
		return updateUI;
	}
	public String add(){
		/*����post����
		 * ��model�е�ֵ����post
		 * ����service��ִ��������
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
