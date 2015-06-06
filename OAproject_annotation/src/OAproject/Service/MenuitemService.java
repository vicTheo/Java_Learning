package OAproject.Service;

import java.util.Collection;

import OAproject.Domain.Menuitem;
import OAproject.Service.Base.BaseService;

public interface MenuitemService{
  public Collection<Menuitem> getAllMenuitems();
  public Collection<Menuitem> getItemById(Long id);
  public Collection<Menuitem> getItemByIds(Long[] ids);
  public Collection<Menuitem> getItemByUidd(Long uid);
  public Collection<Menuitem> getUserMenuitem();
}
