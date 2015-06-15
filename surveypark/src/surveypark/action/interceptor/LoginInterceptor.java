package surveypark.action.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import surveypark.action.BaseAction;
import surveypark.action.LoginAction;
import surveypark.action.RegAction;
import surveypark.action.UserAware;
import surveypark.domain.User;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7027532092043710674L;

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init() {
		// TODO Auto-generated method stub

	}
    
	public String intercept(ActionInvocation invocation) throws Exception {
	    BaseAction action=(BaseAction) invocation.getAction();
	    if(action instanceof LoginAction||action instanceof RegAction){
	    	return invocation.invoke();
	    }else{
	    	//登录判断
	    	HttpSession session=ServletActionContext.getRequest().getSession();
	    	User user=(User) session.getAttribute("user");
	    	if(user==null){
	    		return "login";
	    	}else{
	    		//处理action的user注入问题
	    		if(action instanceof UserAware){
	    			((UserAware)action).setUser(user);
	    		}
	    		return invocation.invoke();
	    	}
	    }
	}

}
