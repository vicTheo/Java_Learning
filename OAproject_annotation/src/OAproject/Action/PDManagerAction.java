package OAproject.Action;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import javax.annotation.Resource;

import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import OAproject.Service.PDManagerService;

@Controller("pdmanagerAction")
@Scope("prototype")
public class PDManagerAction extends ActionSupport{
     @Resource(name="pdmanagerService")
	private PDManagerService pdmanagerService;
     private String key;
     private File resource;
     private InputStream inputStream;
     
     public InputStream getInputStream() {
		return inputStream;
	}
	private String did;
     
	public void setDid(String did) {
		this.did = did;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public File getResource() {
		return resource;
	}
	public void setResource(File resource) {
		this.resource = resource;
	}
	public String getLastVersion(){
    	 Collection<ProcessDefinition> pdlist=this.pdmanagerService.getVersionList();
    	 ActionContext.getContext().put("pdList", pdlist);
    	 return "listAction";
     }
	public String deployUI(){
		return "deployUI";
	}
     public String deploy() throws Exception{
    	 this.pdmanagerService.deploy(this.resource);
    	 return "action2action";
     }    
     
     public String delete(){
    	 this.pdmanagerService.delete(this.key);
    	 return "action2action";
     }
     public String showProcessImage() throws Exception{
    	 System.out.println(this.did);
    	 this.inputStream=this.pdmanagerService.showProcessImage(this.did);
    	 return "success";
     }
}
