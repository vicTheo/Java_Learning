package OAproject.Action;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import OAproject.Action.Base.BaseAction;
import OAproject.Domain.Menuitem;
import OAproject.Service.MenuitemService;
@Controller("menuitemAction")
@Scope("prototype")
public class MenuitemAction extends BaseAction<Menuitem>{
	@Resource(name="menuitemService")
  private MenuitemService menuitemService;

  private Collection<Menuitem> menuitemList;

public Collection<Menuitem> getMenuitemList() {
	return menuitemList;
}
  @JSON(serialize=false)
  public String getAllMenuitems(){
	  this.menuitemList=this.menuitemService.getAllMenuitems();
	  return "success";
  }
  
}
