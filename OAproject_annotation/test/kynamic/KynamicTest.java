package kynamic;


import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;

import OAproject.Domain.Kynamic;
import OAproject.Service.KynamicService;
import base.BaseTest;

public class KynamicTest extends BaseTest{

	@Test
	public void test(){
//		KynamicService kynamicService=(KynamicService) context.getBean("kynamicService");
//	    Kynamic kynamic=new Kynamic();
//	    kynamic.setIsParent(true);
//	    kynamic.setPid(0L);
//	    kynamic.setName("知识管理");
//	    //kynamicService.saveEntry(kynamic);
//	    Collection<Kynamic> list=kynamicService.getAllEntry();
//	    Collection<Kynamic> list=kynamicService.getSibLingsById(4L);
//	    System.out.println(list.size());
//	   Kynamic kynamic=(Kynamic) kynamicService.getParentNode(4L);
//	  System.out.println(kynamic.getName());
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		System.out.println(format.format(new Date()));
	}
}
