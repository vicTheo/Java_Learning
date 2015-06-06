package OAproject.DaoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import OAproject.Dao.KynamicDao;
import OAproject.DaoImpl.BaseDaoImpl.BaseDaoImpl;
import OAproject.Domain.Kynamic;
@Repository("kynamicDao")
public class KynamicDaoImpl extends BaseDaoImpl<Kynamic> implements KynamicDao<Kynamic>{

	public Kynamic getByName(String name) {
		// TODO Auto-generated method stub
		List<Kynamic> list=this.hibernateTemplate.find("from Kynamic where name=?",name);
		if(list.size()!=0){
			return list.get(0);
		}else{
			return null;
		}
		 
	}

}
