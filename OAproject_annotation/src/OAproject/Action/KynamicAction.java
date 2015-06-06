package OAproject.Action;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;



import OAproject.Action.Base.BaseAction;
import OAproject.Domain.Kynamic;
import OAproject.Service.KynamicService;
@Controller("kynamicAction")
@Scope("prototype")
public class KynamicAction extends BaseAction<Kynamic>{
    @Resource(name="kynamicService")
	private KynamicService kynamicService;
   private Long kid;


	public Long getKid() {
	return kid;
}

	private Collection<Kynamic> kynamicList;

	public Collection<Kynamic> getKynamicList() {
		return kynamicList;
	}
    
	public String showKynamicList(){
		this.kynamicList=this.kynamicService.getAllEntry();
		
		return "success";
	}
	
	public String saveKynamic(){
		Kynamic kynamic=new Kynamic();
		BeanUtils.copyProperties(this.getModel(), kynamic);
		this.kynamicService.saveEntry(kynamic);
		this.message="±£´æ³É¹¦";
		this.kid=kynamic.getKid();
		return "success";
	}
	@JSON(serialize=false)
	public String getByName(){
		Kynamic kynamic=this.kynamicService.getByName(this.getModel().getName());
		System.out.println(this.getModel().getName());
		if(kynamic!=null){
			this.message="1";
		}else{
			this.message="2";
		}
		return "success";
	}
}
