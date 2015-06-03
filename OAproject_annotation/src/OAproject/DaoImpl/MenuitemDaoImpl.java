package OAproject.DaoImpl;

import org.springframework.stereotype.Repository;

import OAproject.Dao.MenuitemDao;
import OAproject.DaoImpl.BaseDaoImpl.BaseDaoImpl;
import OAproject.Domain.Menuitem;
@Repository("menuitemDao")
public class MenuitemDaoImpl extends BaseDaoImpl<Menuitem> implements MenuitemDao<Menuitem>{

}
