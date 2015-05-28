package cn.spring.reflect_service;

public class ServiceInvocation {
	//该方法是Service总的调用接口
   public static Object invoke(ServiceMapping serviceMapping){
	  try{ Service service=(Service) Class.forName(serviceMapping.getServiceClass()).newInstance();
       service.service(serviceMapping);}catch(Exception e){
    	   e.printStackTrace();
       }
	  return null;
   }
}
