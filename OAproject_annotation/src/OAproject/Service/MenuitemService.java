package OAproject.Service;

import java.util.Collection;

import OAproject.Domain.Menuitem;
import OAproject.Service.Base.BaseService;

public interface MenuitemService{
  public Collection<Menuitem> getAllMenuitems();
}
