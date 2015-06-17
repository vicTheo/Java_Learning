package surveypark.utils;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import surveypark.service.RightService;

public class GenerateRightUrl {
   public static void main(String[] args) throws Exception{
	   ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
	   RightService rightService=(RightService) context.getBean("rightService");
	  @SuppressWarnings("static-access")
	URL url= GenerateRightUrl.class.getClassLoader().getSystemResource("surveypark/action");
      File dir=new File(url.toURI());
      File[] files=dir.listFiles();
      for(File f:files){
    	  String fname=f.getName();
    	  if(fname.endsWith(".class")&&!fname.equals("BaseAction.class")&&
    			  !fname.equals("interceptor")&&!fname.equals("UserAware.class")){
    		  processClass(fname,rightService);
    	  }
    	
      }
      
   }
 //处理单个类
private static void processClass(String fname,RightService service) throws ClassNotFoundException {
    String packageName="surveypark.action";
    String simpleClassName=fname.substring(0,fname.indexOf("."));
    Class classt=Class.forName(packageName+"."+simpleClassName);
    Method[] methods=classt.getDeclaredMethods();
    String url="";
    Class retType=null;
    Class[] paramsType=null;
    String mname=null;
    for(Method m:methods){
    	mname=m.getName();
    	retType=m.getReturnType();
    	paramsType=m.getParameterTypes();
    	if(retType==String.class&&!ValidateUtil.isValid(paramsType)
    			&&Modifier.isPublic(m.getModifiers())){
    		if(mname.equals("execute")){
    			url="/"+simpleClassName;
    		}else{
    			url="/"+simpleClassName+"_"+mname;
    		}
    		service.appendRightsUrl(url);
    	}
    	
    }
   
}

}
