package Exception;

import com.opensymphony.xwork2.ActionContext;

public class OAException {
  /*
   * ����
   */
	public void getExceptionMessage(Throwable ex){
		//��service����쳣��Ϣѹ��ջ��
		ActionContext.getContext().getValueStack().push(ex);
		
	}
}
