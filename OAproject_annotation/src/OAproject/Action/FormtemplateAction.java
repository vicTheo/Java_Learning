package OAproject.Action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;

import javax.annotation.Resource;

import org.jbpm.api.ProcessDefinition;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import OAproject.Action.Base.BaseAction;
import OAproject.Domain.FormTemplate;
import OAproject.Service.FormTemplateService;
import OAproject.Service.PDManagerService;
import OAproject.util.UploadUtils;
@Controller("formTemplateAction")
@Scope("prototype")
public class FormtemplateAction extends BaseAction<FormTemplate>{
   @Resource(name="formTemplateService")
   private FormTemplateService formTemplateService;
   @Resource(name="pdmanagerService")
   private PDManagerService pdManagerService;
   private File resource;
   private InputStream inputStream;
   
   public InputStream getInputStream() {
	return inputStream;
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
public String getAllFormTemplates(){
	   Collection<FormTemplate> ftList=this.formTemplateService.getAllEntry();
	   ActionContext.getContext().put("ftList", ftList);
	   return listAction;
   }
   public String addUI(){
	   Collection<ProcessDefinition> pdList=this.pdManagerService.getVersionList();
	   ActionContext.getContext().put("pdList", pdList);
	   return "addUI";
   }
   public String add(){
	   FormTemplate formTemplate=new FormTemplate();
	   BeanUtils.copyProperties(this.getModel(), formTemplate);
	   String path=UploadUtils.saveUploadFile(this.resource);
	   System.out.println(path);
	   formTemplate.setUrl(path);
	   this.formTemplateService.saveEntry(formTemplate);
	   return action2action;
   }
   
   public String download() throws Exception{
	   this.inputStream=this.formTemplateService.download(this.getModel().getFtid());
	   return "success";
   }

}
