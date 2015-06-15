package surveypark.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.FileUploadInterceptor;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;
import org.springframework.stereotype.Controller;

import surveypark.domain.Survey;
import surveypark.domain.User;
import surveypark.service.SurveyService;
import surveypark.utils.ValidateUtil;
@Controller("surveyAction")
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey> implements UserAware ,ServletContextAware{
	private static final long serialVersionUID = -3427479652228070392L;
    @Resource(name="surveyService")
   private SurveyService surveyService;
   private Map<String, Object> sessionMap;
   private List<Survey> surveyList;
   private Integer sid;


	public Integer getSid() {
	return sid;
}

public void setSid(Integer sid) {
	this.sid = sid;
}
	//接受user对象
    private User user;
   
    public List<Survey> getSurveyList() {
	return surveyList;
}

public void setSurveyList(List<Survey> surveyList) {
	this.surveyList = surveyList;
}

	/*
     * 新建调查
     */
	public String newSurvey(){
		this.model=surveyService.newSurvey(user);
		
		return "designSurveyPage";
	}
	public String designSurvey(){
		this.model=surveyService.getSurveyWithChildren(sid);
		return "designSurveyPage";
	}
	
	public String getMySurveys(){
		
		this.surveyList=surveyService.getMySurveys(user);
		return "mySurveyList";
	}
	public String editSurvey(){
		this.model=surveyService.getById(sid);
		return "editSurvey";
	}
	public String updateSurvey(){
		//用于重定向
		this.sid=model.getId();
		//用于维护关系
		model.setUser(user);
		surveyService.updateSurvey(model);
		return "designSurveyAction";
	}
    //注入user对象
	public void setUser(User user) {
		this.user=user;
		
	}
/*	
	 * 此方法在designSurvey之前，及getModel()方法之前执行
	 
	public void prepareDesignSurvey(){
	
		this.model=surveyService.getById(id);
}*/

	//删除调查
	public String deleteSurvey(){
		surveyService.deleteSurvey(sid);
		return "getMySurveysAction";
	}
	//清除答案
	public String clearAnswers(){
		surveyService.clearAnswers(sid);
		return "getMySurveysAction";
	}
	//改变调查状态
	public String toogleStatus(){
		surveyService.toogleStatus(sid);
		return "getMySurveysAction";
	}
	//到addLogoPage
	public String toAddLogoPage(){
		return "toAddLogoPage";
	}
	private String inputPage;
	
	public String getInputPage() {
		return inputPage;
	}

	public void setInputPage(String inputPage) {
		this.inputPage = inputPage;
	}
	//上传文件
	private File logoPhoto;
	private String logoPhotoFileName;
	
	//接受servletContext
	private ServletContext servletContext;


	public File getLogoPhoto() {
		return logoPhoto;
	}

	public void setLogoPhoto(File logoPhoto) {
		this.logoPhoto = logoPhoto;
	}

	public String getLogoPhotoFileName() {
		return logoPhotoFileName;
	}

	public void setLogoPhotoFileName(String logoPhotoFileName) {
		this.logoPhotoFileName = logoPhotoFileName;
	}

	//addLogo
	public String doAddLogo(){
		if(ValidateUtil.isValid(logoPhotoFileName)){
		String dir=servletContext.getRealPath("/upload");
		Long l=System.nanoTime();
		String ext=logoPhotoFileName.substring(logoPhotoFileName.lastIndexOf("."));
		logoPhoto.renameTo(new File(dir,l+ext));
		//更新数据库路径信息
		surveyService.updateLogoPhotoPath(sid,"/upload/"+l+ext);
		}
		return "designSurveyAction";
	}
    //注入servletContext
	public void setServletContext(ServletContext arg0) {
		this.servletContext=arg0;
	}
	//在doAddLogo（）之前执行
	public void prepareDoAddLogo(){
		this.inputPage="/addLogo.jsp";
	}
	//判断logo是否存在
	public boolean logoPhotoExists(){
		String path=model.getLogoPhotpPath();
		if(ValidateUtil.isValid(path)){
		  String realPath=servletContext.getRealPath(path);
		  boolean b=new File(realPath).exists();
		  return b;
		}
		return false;
	}
	//分析
	public String analyzeSurvey(){
		this.model=surveyService.getSurveyWithChildren(sid);
	   return "analyzeSurveyList";	
	}
} 
