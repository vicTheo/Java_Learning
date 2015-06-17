package surveypark.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import surveypark.domain.User;
import surveypark.service.RightService;
import surveypark.service.UserService;
import surveypark.utils.DataUtil;
@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends BaseAction<User> implements SessionAware{
    
	private static final long serialVersionUID = 7869118308388627250L;
    @Resource(name="userService")
	private UserService userService;
    @Resource(name="rightService")
    private RightService rightService;
	private Map<String, Object> sessionMap;
    //到登陆页面
    public String toLoginPage(){
    	return "loginPage";
    }
    //进行登录
    public String doLogin(){
    	
    	return SUCCESS;
    }
    /*
     * 次验证方法在doLogin()之前执行
     */
    public void validateDoLogin(){
    	User user=userService.validateEmailAndPwd(this.getModel().getEmail(),DataUtil.md5(this.getModel().getPassword()));
    	if(user==null){
    		addActionError("email/password不正确");
    	}else{
    		//初始化权限总合数组
    		int rmaxRightPos=rightService.getMaxRightPos();
    		user.setRightSum(new long[rmaxRightPos+1]);
    		//计算权限综合
    		user.calculateRightSum();
    		sessionMap.put("user", user);
    	}	
    	
    }
    //注入session的map
	public void setSession(Map<String, Object> arg0) {
      		this.sessionMap=arg0;
	}
}
