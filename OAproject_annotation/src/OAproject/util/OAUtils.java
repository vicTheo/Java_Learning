package OAproject.util;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import OAproject.Domain.User;

public class OAUtils {
   public static Long[] string2longArray(String str){
	   String[] strArr=str.split(",");
	   Long[] longArr=new Long[strArr.length]; 
	   int index=0;
	   for(String strr:strArr){
		   longArr[index]=Long.valueOf(strr);
		   index++;
	   }
	   return longArr;
   }
   
   public static User getUserFromSession() {
	   return (User) ServletActionContext.getRequest().getSession().getAttribute("user");
	   
   }
   public static void putUser2Session(User user){
	   ServletActionContext.getRequest().getSession().setAttribute("user", user);
   }
}
