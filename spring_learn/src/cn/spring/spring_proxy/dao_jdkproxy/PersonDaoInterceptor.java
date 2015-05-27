package cn.spring.spring_proxy.dao_jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PersonDaoInterceptor implements InvocationHandler{
  private Transaction transaction;
  private Object target;
  public PersonDaoInterceptor(Transaction transaction,Object obj){
	  this.target=obj;
	  this.transaction=transaction;
  }
public Object invoke(Object proxy, Method method, Object[] args)
		throws Throwable {
	Object obj;
    
    String methodName=method.getName();
    if("savePerson".equals(methodName)||"updatePerson".equals(methodName)||
    		"deletePerson".endsWith(methodName)){
    	this.transaction.beginTransaction();
    	obj=method.invoke(this.target, args);
    	this.transaction.commit();
    }else{
    	obj=method.invoke(this.target, args);
    }
    
	return obj;
}
  
}
