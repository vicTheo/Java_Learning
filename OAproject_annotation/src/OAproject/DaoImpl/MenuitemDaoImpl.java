package OAproject.DaoImpl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import OAproject.Dao.MenuitemDao;
import OAproject.Dao.UserDao;
import OAproject.DaoImpl.BaseDaoImpl.BaseDaoImpl;
import OAproject.Domain.Menuitem;
import OAproject.Domain.User;
import OAproject.util.OAUtils;
@Repository("menuitemDao")
public class MenuitemDaoImpl extends BaseDaoImpl<Menuitem> implements MenuitemDao<Menuitem>{
    @Resource(name="userDao")
	private UserDao userDao;
	public Collection<Menuitem> getAllItems() {
		List<Menuitem> list=this.hibernateTemplate.find("from Menuitem");
		return new HashSet<Menuitem>(list);
	}

	public Collection<Menuitem> getItemById(Long id) {
		// TODO Auto-generated method stub
		return this.hibernateTemplate.find("from Menuitem  where pid=?",id);
	}

	public Collection<Menuitem> getItemByIds(Long[] ids) {
		// TODO Auto-generated method stub
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("from Menuitem");
		stringBuffer.append(" where mid in(");
		for(int i=0;i<ids.length;i++){
			if(i<ids.length-1){
				stringBuffer.append(ids[i]+",");
			}else{
				stringBuffer.append(ids[i]);
			}
		}
		stringBuffer.append(")");
		List<Menuitem> menuitemList = this.hibernateTemplate.find(stringBuffer.toString());
		return new HashSet<Menuitem>(menuitemList);
	}

	public Collection<Menuitem> getItemByUid(Long uid) {
		/*
		 * 如果是admin则把所有的菜单的checked设置为true
		 * 如果不是amdin,则先遍历所有的菜单项，再遍历用户能访问到的菜单项，然后把所有的菜单项中，用户能够访问到的checked设置为true
		 */
		/**
		 * 当user被提取出来以后，session已经关闭了
		 */
		User user=(User) this.userDao.getDEntryById(uid);
		Collection<Menuitem> itemList=this.getAllItems();
		Collection<Menuitem> userMenuitemList=this.hibernateTemplate.find("from Menuitem m inner join fetch m.users u where u.uid=?",uid);
		if(user.getUsername().equals("admin")){
			for(Menuitem menuitem:itemList){
				menuitem.setChecked(true);
			}
		}else{
			for(Menuitem menuitem:itemList){
				for(Menuitem menuitem2:userMenuitemList){
					if(menuitem.getMid().longValue()==menuitem2.getMid().longValue()){
						menuitem.setChecked(true);
					}
				}
			}
		}
		return itemList;
	}
   
	public Collection<Menuitem> getUserMenuitem(){
		User user=OAUtils.getUserFromSession();
		if(user.getUsername().equals("admin")){
			return this.getAllItems();
		}else{
			List<Menuitem> list= this.hibernateTemplate.find("from Menuitem m inner join fetch m.users u where u.uid=?",user.getUid());
			return list;
		}
		
	
	}
}
