package OAproject.Action;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import OAproject.Action.Base.BaseAction;
import OAproject.Domain.Menuitem;
import OAproject.Domain.User;
import OAproject.Service.MenuitemService;
import OAproject.Service.UserService;
import OAproject.util.OAUtils;
@Controller("menuitemAction")
@Scope("prototype")
public class MenuitemAction extends BaseAction<Menuitem>{
	@Resource(name="menuitemService")
  private MenuitemService menuitemService;
	@Resource(name="userService")
  private UserService userService;
	
  private Collection<Menuitem> menuitemList;

  public Collection<Menuitem> getMenuitemList() {
	return menuitemList;
}
  private Long uid;
  private String mids;

public void setUid(Long uid) {
	this.uid = uid;
}


public void setMids(String mids) {
	this.mids = mids;
}

@JSON(serialize=false)
  public String showAllMenuitems(){
	
	  this.menuitemList=this.menuitemService.getAllMenuitems();
	  System.out.println(this.menuitemList.size());
	  return "success";
  }
  
   public String showPrivilegeByUid(){
	this.menuitemList=this.menuitemService.getItemByUidd(this.uid);
	return "success";
   }
  public String showItemById(){
	  this.menuitemList=menuitemService.getItemById(this.getModel().getPid());
	  return "success";
  }
  
  
  public String savePrivilege(){
		User user = (User) this.userService.getEntryById(this.uid);
		Set<Menuitem> menuitems = (Set<Menuitem>) this.menuitemService.getItemByIds((OAUtils.string2longArray(this.mids)));
		user.setMenuitems(menuitems);
		this.userService.updateEntry(user);
		return "success";
  }
  @JSON(serialize=false)
  public String getUserMenuitem(){
	  this.menuitemList=this.menuitemService.getUserMenuitem();
	  return "success";
  }
}
