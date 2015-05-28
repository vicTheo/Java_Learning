package cn.spring.reflect_service;

import org.junit.Test;

public class test {
   
	@Test
	public void test() throws Exception{
		ServiceMapping serviceMapping=new ServiceMapping();
		serviceMapping.setMethod("savePerson");
		serviceMapping.setServiceClass("cn.spring.reflect_service.PersonService");
		ServiceInvocation.invoke(serviceMapping);
	}
}
