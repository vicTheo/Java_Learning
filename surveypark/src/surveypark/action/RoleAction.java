package surveypark.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import surveypark.domain.security.Right;
import surveypark.domain.security.Role;
import surveypark.service.RightService;
import surveypark.service.RoleService;
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
	private static final long serialVersionUID = -526507140928894083L;
    
	@Resource(name="roleService")
	private RoleService roleService;
	@Resource(name="rightService")
	private RightService rightService;
	private List<Role> allRoles;
	private Integer roleId;
	private List<Right> noOwnRights;
	private List<Right> ownRights;
	private Integer[] noOwnRightIds;
	private Integer[] ownRightIds;
	
	public List<Right> getNoOwnRights() {
		return noOwnRights;
	}
	public void setNoOwnRights(List<Right> noOwnRights) {
		this.noOwnRights = noOwnRights;
	}
	public List<Right> getOwnRights() {
		return ownRights;
	}
	public void setOwnRights(List<Right> ownRights) {
		this.ownRights = ownRights;
	}
	public Integer[] getNoOwnRightIds() {
		return noOwnRightIds;
	}
	public void setNoOwnRightIds(Integer[] noOwnRightIds) {
		this.noOwnRightIds = noOwnRightIds;
	}
	public Integer[] getOwnRightIds() {
		return ownRightIds;
	}
	public void setOwnRightIds(Integer[] ownRightIds) {
		this.ownRightIds = ownRightIds;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public List<Role> getAllRoles() {
		return allRoles;
	}
	public void setAllRoles(List<Role> allRoles) {
		this.allRoles = allRoles;
	}
	
	public String getRoleList(){
		this.allRoles=roleService.getAllEntities();
		return "roleListPage";
	}
	
	public String toAddRolePage(){
		this.noOwnRights=rightService.getAllEntities();
		return "toAddRolePage";
	}
	
	public String saveOrUpdateRole(){
		roleService.saveOrUpdateEntity(model,ownRightIds);
		return "roleListPageAction";
	}
	public String editRole(){
		this.model=roleService.getEntity(roleId);
		this.noOwnRights=rightService.findRightsNotInRange(model.getRights());
		return "editRolePage";
	}
	public String deleteRole(){
		Role role=new Role();
		role.setId(roleId);
		roleService.deleteEntity(role);
		return "roleListPageAction";
	}
}
