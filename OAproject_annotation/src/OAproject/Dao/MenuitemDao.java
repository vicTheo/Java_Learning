package OAproject.Dao;

import java.util.Collection;

import OAproject.Dao.BaseDao.BaseDao;
import OAproject.Domain.Menuitem;

public interface MenuitemDao<T> extends BaseDao<T> {
   public Collection<T> getAllItems();
   public Collection<T> getItemById(Long id);
   public Collection<T> getItemByIds(Long[] ids);
   public Collection<T> getItemByUid(Long uid);
   public Collection<Menuitem> getUserMenuitem();
}
