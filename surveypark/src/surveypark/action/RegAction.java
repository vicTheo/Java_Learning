package surveypark.action;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import surveypark.domain.User;
import surveypark.service.UserService;
import surveypark.utils.DataUtil;
import surveypark.utils.ValidateUtil;
@Controller("regAction")
@Scope("prototype")
public class RegAction extends BaseAction<User> {
    @Resource(name="userService")
	private UserService userService;
	private static final long serialVersionUID = 1L;
	private String confirmPassword;
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

    //到达注册页面
	@SkipValidation
	public String toRegPage(){
		return "regPage";
	}
	//进行注册
	public String doReg(){
		model.setPassword(DataUtil.md5(this.getModel().getPassword()));
		userService.saveEntity(model);
		return "success";
	}
	/*
	 * 校验
	 */
	public void validate(){
		//非空校验
		if(!ValidateUtil.isValid(this.getModel().getNickName())){
			addFieldError("nickName", "昵称不能为空！");
		}
		if(!ValidateUtil.isValid(this.getModel().getEmail())){
			addFieldError("email", "邮箱不能为空！");
		}
		if(!ValidateUtil.isValid(this.getModel().getPassword())){
			addFieldError("password", "密码不能为空！");
		}
		if(this.hasErrors()){
			return ;
		}
		//密码一致性校验
		if(!this.getModel().getPassword().equals(confirmPassword))
		{
			addFieldError("password", "两次输入的密码不一致");
			return ;
		}
		//email是否被占用校验
		if(!userService.validateEmail(this.getModel().getEmail())){
			addFieldError("email", "邮箱已被占用");
			return ;
		}
	}
}
