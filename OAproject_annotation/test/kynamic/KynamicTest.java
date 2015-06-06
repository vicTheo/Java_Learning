package kynamic;

import java.util.Collection;

import org.junit.Test;

import OAproject.Domain.Kynamic;
import OAproject.Service.KynamicService;
import base.BaseTest;

public class KynamicTest extends BaseTest{

	@Test
	public void test(){
		KynamicService kynamicService=(KynamicService) context.getBean("kynamicService");
	    Kynamic kynamic=new Kynamic();
	    kynamic.setIsParent(true);
	    kynamic.setPid(0L);
	    kynamic.setName("知识管理");
	    //kynamicService.saveEntry(kynamic);
	    Collection<Kynamic> list=kynamicService.getAllEntry();
	    System.out.println(list.size());
	}
}
