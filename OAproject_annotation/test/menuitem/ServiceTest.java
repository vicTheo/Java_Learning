package menuitem;

import java.util.Collection;

import org.junit.Test;

import OAproject.Dao.MenuitemDao;
import OAproject.Domain.Menuitem;
import OAproject.Service.MenuitemService;
import base.BaseTest;

public class ServiceTest  extends BaseTest{
	
	@Test
	public void test(){
		
//		MenuitemService muService=(MenuitemService) context.getBean("menuitemService");
//	    System.out.println(muService.getAllMenuitems().size());
	  
		MenuitemDao dao=(MenuitemDao) context.getBean("menuitemDao");
		MenuitemService ms=(MenuitemService) context.getBean("menuitemService");
	    //System.out.println(dao.getAllItems());
	    Long[] lo={3L,2L};
	    
	    Collection<Menuitem> coll=dao.getItemByIds(lo);
	    Collection<Menuitem> coll2=ms.getItemByIds(lo);
	    System.out.println(coll.size());
	}

}
