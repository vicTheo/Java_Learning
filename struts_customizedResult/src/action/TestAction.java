package action;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport{
   public String showChart(){
	   System.out.println("testAction--chart");
	return "chart";
	   
	   
   }
}
