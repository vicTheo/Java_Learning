package Exception;

import com.opensymphony.xwork2.ActionContext;

public class OAException {
  /*
   * 切面
   */
	public void getExceptionMessage(Throwable ex){
		//将service层的异常信息压入栈中
		ActionContext.getContext().getValueStack().push(ex);
		
	}
}
