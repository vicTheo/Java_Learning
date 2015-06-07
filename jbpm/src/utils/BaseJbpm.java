package utils;

import org.jbpm.api.Configuration;
import org.jbpm.api.ProcessEngine;
import org.junit.Before;

public class BaseJbpm {
	public static ProcessEngine processEngine;
	
	@Before
	public void testBase(){
		this.processEngine=Configuration.getProcessEngine();
	}

}
