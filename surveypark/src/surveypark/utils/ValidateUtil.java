package surveypark.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import surveypark.action.BaseAction;
import surveypark.action.UserAware;
import surveypark.domain.User;
import surveypark.domain.security.Right;

public class ValidateUtil {
 public static boolean isValid(Object[] values){
	 if(values==null||values.length==0){
		 return false;
	 }
	 return true;
 }
 public static boolean isValid(Collection list){
	 if(list==null||list.isEmpty()){
		 return true;
	 }
	 return false;
 }
public static boolean isValid(String str) {
	 if(str==null||"".equals(str.trim())){
		 return false;
	 }
	
	return true;
}
//检测权限
public static boolean hasRight(String namespace,String actionname,HttpServletRequest request,BaseAction action){
	if(!ValidateUtil.isValid(namespace)||"/".equals(namespace)){
		namespace="";
	}
	if(actionname.contains("?")){//处理参数
		actionname=actionname.substring(0,actionname.indexOf("?"));
	}
	String url=namespace+"/"+actionname;
	Map<String,Right> map=(Map<String, Right>) ServletActionContext.getServletContext().getAttribute("all_rights_map");
    Right r=map.get(url);
    if(r==null||r.isCommon()){
    	return true;
    }else{
    	User user=(User) request.getSession().getAttribute("user");
    	if(user==null){
    		return false;
    	}else{
    		if(action!=null&&action instanceof UserAware){//处理useraware
    			((UserAware)action).setUser(user);
    		}
    		if(user.isSuperAdmin()){//超级管理员
    			return true;
    		}else{
    			if(user.hasRight(r)){//有权限
    				return true;
    			}else{
    				return false;
    			}
    		}
    	}
    }
}
}
