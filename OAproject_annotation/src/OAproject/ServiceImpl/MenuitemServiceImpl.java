package OAproject.ServiceImpl;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import OAproject.Dao.MenuitemDao;
import OAproject.Domain.Menuitem;
import OAproject.Service.MenuitemService;
@Service("menuitemService")
public class MenuitemServiceImpl implements MenuitemService{
    @Resource(name="menuitemDao")
	private MenuitemDao menuitemDao;

	public Collection<Menuitem> getAllMenuitems() {
		// TODO Auto-generated method stub
		
		return this.menuitemDao.getAllEntry();
	}
	


}
