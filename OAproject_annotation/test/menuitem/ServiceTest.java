package menuitem;

import org.junit.Test;

import OAproject.Dao.MenuitemDao;
import OAproject.Service.MenuitemService;
import base.BaseTest;

public class ServiceTest  extends BaseTest{
	
	@Test
	public void test(){
		
//		MenuitemService muService=(MenuitemService) context.getBean("menuitemService");
//	    System.out.println(muService.getAllMenuitems().size());
	  
		MenuitemDao dao=(MenuitemDao) context.getBean("menuitemDao");
	    System.out.println(dao.getAllEntry().size());
	}

}
