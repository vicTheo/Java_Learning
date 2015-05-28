package cn.spring.reflect_service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ServiceImpl implements Service{

	public Object service(ServiceMapping serviceMapping) throws Exception {
		String methodName=serviceMapping.getMethod();
		Object obj=Class.forName(serviceMapping.getServiceClass()).newInstance();
	    Method method=Class.forName(serviceMapping.getServiceClass()).getMethod(methodName);
	    return method.invoke(obj);
	}

}
