package OAproject.Action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import OAproject.Action.Base.BaseAction;
import OAproject.Domain.User;
import OAproject.Service.LoginService;
import OAproject.util.OAUtils;
@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends BaseAction<User>{
  @Resource(name="loginService")
	private LoginService loginService;
	
  public String login(){
	  User user=this.loginService.checkUandP(this.getModel());
	  if(user!=null){
		 OAUtils.putUser2Session(user);
		 return "index";
	  }else{
		  
		  
	  }
	 return null;
  }
}
