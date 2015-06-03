package OAproject.Action.result;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;

@SuppressWarnings("serial")
public class AjaxValidation implements Result{

	public void execute(ActionInvocation arg0) throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		out.print(ActionContext.getContext().getValueStack().peek());
		out.close();
	}

}
