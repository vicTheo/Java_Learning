package surveypark.action.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import surveypark.action.BaseAction;
import surveypark.action.LoginAction;
import surveypark.action.RegAction;
import surveypark.action.UserAware;
import surveypark.domain.User;
import surveypark.domain.security.Right;
import surveypark.utils.ValidateUtil;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class RightFilterInterceptor implements Interceptor {

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
    //权限表过滤器
	public String intercept(ActionInvocation invocation) throws Exception {
		BaseAction action=(BaseAction) invocation.getAction();
	    ActionProxy ap=invocation.getProxy();
	    String namespace=ap.getNamespace();
	    String actionName=ap.getActionName();
	    HttpServletRequest request=ServletActionContext.getRequest();
	    if(ValidateUtil.hasRight(namespace, actionName, request, action)){
	    	return invocation.invoke();
	    }else{
	    	return "no_right_error";
	    }
	}

}
