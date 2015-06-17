package surveypark.action.interceptor;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import surveypark.service.RightService;
import surveypark.utils.ValidateUtil;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class CatchRightUrlInterceptor implements Interceptor{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		ActionProxy ap=invocation.getProxy();
		String namespace=ap.getNamespace();
		if(namespace.equals("/")||!ValidateUtil.isValid(namespace)){
			namespace="";
		}
		String url=namespace+"/"+ap.getActionName();
		ServletContext sc=ServletActionContext.getServletContext();
		//WebApplicationContext wac=(WebApplicationContext) sc.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		WebApplicationContext wac=WebApplicationContextUtils.getWebApplicationContext(sc);
		RightService rs=(RightService) wac.getBean("rightService");
		rs.appendRightsUrl(url);
		return invocation.invoke();
	}

}
