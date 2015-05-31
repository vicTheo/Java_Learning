package result;

import org.apache.struts2.dispatcher.StrutsResultSupport;

import com.opensymphony.xwork2.ActionInvocation;

public class OaResult extends StrutsResultSupport {
  private String oa;
  
	public String getOa() {
	return oa;
}

public void setOa(String oa) {
	this.oa = oa;
}

	@Override
	protected void doExecute(String arg0, ActionInvocation arg1)
			throws Exception {
	      System.out.println(this.oa);
		
	}

}
