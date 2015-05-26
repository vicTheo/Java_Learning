package cn.spring.interfaceOriented;

import org.junit.Test;

public class test extends SpringHelper{
   static{
	   path="cn/spring/interfaceOriented/applicationContext.xml";
   }
	@Test
	public void test(){
		Doucument document=new PdfDocument();
		DocumentManager dm=new DocumentManager(document);
		dm.read();
		dm.write();
	}
	@Test
	public void test2(){
		DocumentManager dm=(DocumentManager) context.getBean("documentManager");
		dm.read();
		dm.write();
	}
}
