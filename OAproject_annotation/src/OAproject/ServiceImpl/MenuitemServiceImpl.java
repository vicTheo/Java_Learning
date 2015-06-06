package OAproject.ServiceImpl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import OAproject.Dao.MenuitemDao;
import OAproject.Domain.Menuitem;
import OAproject.Service.MenuitemService;
@Service("menuitemService")
public class MenuitemServiceImpl implements MenuitemService{
    @Resource(name="menuitemDao")
	private MenuitemDao menuitemDao;

	public Collection<Menuitem> getAllMenuitems() {
		// TODO Auto-generated method stub
		
		return this.menuitemDao.getAllItems();
	}

	public Collection<Menuitem> getItemById(Long id) {
		// TODO Auto-generated method stub
		return this.menuitemDao.getItemById(id);
	}
    @Transactional(readOnly=false)
	public Collection<Menuitem> getItemByIds(Long[] ids) {
		// TODO Auto-generated method stub
		return this.menuitemDao.getItemByIds(ids);
	}

	public Collection<Menuitem> getItemByUidd(Long uid) {
		// TODO Auto-generated method stub
		
		return this.menuitemDao.getItemByUid(uid);
	}

	public Collection<Menuitem> getUserMenuitem() {
		// TODO Auto-generated method stub
		return this.menuitemDao.getUserMenuitem();
	}
	


}
