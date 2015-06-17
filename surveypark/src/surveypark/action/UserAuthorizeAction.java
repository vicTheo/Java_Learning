package surveypark.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import surveypark.domain.User;
import surveypark.domain.security.Role;
import surveypark.service.RoleService;
import surveypark.service.UserService;
@Controller("userAuthorizeAction")
@Scope("prototype")
public class UserAuthorizeAction extends BaseAction<User> {

	private static final long serialVersionUID = -3977195825021168689L;
    @Resource(name="userService")
    private UserService userService;
    @Resource(name="roleService")
    private RoleService roleService;
    private List<User> allUsers;
    private List<Role> noOwnRoles;
    private Integer[] ownRoleIds;
    private Integer[] noOwnRoleIds;
    private Integer userId;
    
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public List<User> getAllUsers() {
		return allUsers;
	}
	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}
	public List<Role> getNoOwnRoles() {
		return noOwnRoles;
	}
	public void setNoOwnRoles(List<Role> noOwnRoles) {
		this.noOwnRoles = noOwnRoles;
	}

	public Integer[] getOwnRoleIds() {
		return ownRoleIds;
	}
	public void setOwnRoleIds(Integer[] ownRoleIds) {
		this.ownRoleIds = ownRoleIds;
	}
	public Integer[] getNoOwnRoleIds() {
		return noOwnRoleIds;
	}
	public void setNoOwnRoleIds(Integer[] noOwnRoleIds) {
		this.noOwnRoleIds = noOwnRoleIds;
	}
    
    public String toUserAuthorizeList(){
    	this.allUsers=userService.getAllEntities();
    	return "toUserAuthorizeList";
    }
    
    public String editAuthorize(){
    	this.model=userService.getEntity(userId);
    	this.noOwnRoles=roleService.findRolesNotInRange(model.getRoles());
    	return "userAuthorizePage";
    }
    
    public String updateAuthorize(){
    	User user=userService.getEntity(model.getId());
    	userService.updateAuthorize(user,ownRoleIds);
    	return "toUserAuthorizeListAction";
    }
    
    public String clearAuthorize(){
    	userService.clearAuthorization(userId);
    	return "toUserAuthorizeListAction";
    }
}
