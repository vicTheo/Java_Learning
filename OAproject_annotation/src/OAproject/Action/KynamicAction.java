package OAproject.Action;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;



import OAproject.Action.Base.BaseAction;
import OAproject.Domain.Kynamic;
import OAproject.Domain.Version;
import OAproject.Service.KynamicService;
import OAproject.Service.VersionService;
@Controller("kynamicAction")
@Scope("prototype")
public class KynamicAction extends BaseAction<Kynamic>{
    @Resource(name="kynamicService")
	private KynamicService kynamicService;
  
   private Long kid;


	public Long getKid() {
	return kid;
}

	private Kynamic kynamic;
	
	public Kynamic getKynamic() {
		return kynamic;
	}

	private Collection<Kynamic> kynamicList;

	public Collection<Kynamic> getKynamicList() {
		return kynamicList;
	}
    
	private Collection<Version> versionList;
	
	public Collection<Version> getVersionList() {
		return versionList;
	}

	public String showKynamicList(){
		this.kynamicList=this.kynamicService.getAllEntry();
		
		return "success";
	}
	
	public String saveKynamic(){
		Kynamic kynamic=new Kynamic();
		BeanUtils.copyProperties(this.getModel(), kynamic);
		this.kynamicService.saveEntry(kynamic);
		this.message="保存成功";
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
	
	public String deleteNode(){
		this.kynamicService.deleteEntryById(this.getModel().getKid());
		this.message="删除成功";
		return "success";
	}
	@JSON(serialize=false)
	public String getSibLingNode(){
	    this.kynamicList=this.kynamicService.getSibLingsById(this.getModel().getKid());
		return "success";
	}
	@JSON(serialize=false)
	public String getParentNode(){
		this.kynamic=(Kynamic) this.kynamicService.getParentNode(this.getModel().getKid());
		return "success";
	}
	public String updateNode(){
		Kynamic kynamic =(Kynamic) this.kynamicService.getEntryById(this.getModel().getKid());
		kynamic.setName(this.getModel().getName());
		this.kynamicService.updateEntry(kynamic);
		this.message="操作成功";
		return "success";
	}
	@JSON(serialize=false)
	public String getVersionByKid(){
		this.versionList=this.kynamicService.getVersionByKid(this.getModel().getKid());
		return "success";
	}
}
