package surveypark.advice;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.ProceedingJoinPoint;

import com.opensymphony.xwork2.ActionContext;

import surveypark.domain.User;
import surveypark.domain.log.Log;
import surveypark.service.LogService;
import surveypark.utils.StringUtil;

/*
 * 日志切面
 */
public class Logger {
	// 注入logService
	private LogService logService;

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	// 记录日志
	public Object record(ProceedingJoinPoint pjp) {
		Log log = new Log();
		try {
			ActionContext ac = ActionContext.getContext();
			if (ac != null) {
				HttpServletRequest request = (HttpServletRequest) ac
						.get(ServletActionContext.HTTP_REQUEST);
				if (request != null) {
					User user = (User) request.getSession()
							.getAttribute("user");
					if (user != null) {
						// 设置操作人
						log.setOperator(user.getId() + ":" + user.getEmail());
					}
				}
			}
			// 方法名
			String operName = pjp.getSignature().getName();
			log.setOperName(operName);
			// 参数列表
			Object[] params = pjp.getArgs();
			log.setOperParams(StringUtil.arr2string(params));
			// 调用目标对象方法
			Object ret = pjp.proceed();

			log.setOperResult("success");

			if (ret != null) {
				log.setResultMsg(ret.toString());
			}
			return ret;
		} catch (Throwable e) {
			log.setOperResult("failture");
			log.setResultMsg(e.getMessage());
		} finally {
			logService.saveEntity(log);
		}
		return null;
	}
}
