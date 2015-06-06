package OAproject.Action;

import java.util.Date;

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
@Controller("versionAction")
@Scope("prototype")
public class VersionAction extends BaseAction<Version>{

	  @Resource(name="versionService")
	    private VersionService versionService;
	  @Resource(name="kynamicService")
	  private KynamicService kynamicService;
	  private Version version;
	  
	  public Version getVersion() {
		return version;
	}
    private Long kid;
    
	public void setKid(Long kid) {
		this.kid = kid;
	}
	@JSON(serialize=false)
	  public String getVersionByVid(){
		  Version version=(Version) this.versionService.getEntryById(this.getModel().getVid());
		  this.version=version;
		  return "success";
	  }
	public String saveVersion(){
		Version version=new Version();
		Kynamic kynamic =(Kynamic) this.kynamicService.getEntryById(this.kid);
		BeanUtils.copyProperties(this.getModel(), version);
		Date date=new Date();
		version.setKynamic(kynamic);
		Long vid=this.versionService.getMaxVersionId(this.kid);
		if(vid!=null){
			version.setVersion(vid+1L);}else{
			version.setVersion(1L);
		}
		
		version.setUpdateTime(date);
		this.versionService.saveEntry(version);
		this.message="保存成功";
		return "success";
	}
	public String deleteVersion(){
		this.versionService.deleteEntryById(this.getModel().getVid());
		this.message="删除成功";
		return "success";
	}
}
