package OAproject.DaoImpl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import OAproject.Dao.KynamicDao;
import OAproject.DaoImpl.BaseDaoImpl.BaseDaoImpl;
import OAproject.Domain.Kynamic;
import OAproject.Domain.Version;
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

	public Collection<Kynamic> getSibLingsById(Long id) {
		// TODO Auto-generated method stub
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("from Kynamic");
		stringBuffer.append(" where pid=(");
		stringBuffer.append("select pid from Kynamic where kid=?)");
		
		return this.hibernateTemplate.find(stringBuffer.toString(),id);
	}

	public Kynamic getParentNode(Long id) {
		// TODO Auto-generated method stub
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("from Kynamic");
		stringBuffer.append(" where kid=(");
		stringBuffer.append("select pid from Kynamic where kid=?)");
		List<Kynamic> list=this.hibernateTemplate.find(stringBuffer.toString(),id);
		if(list.size()!=0){
			return list.get(0);
		}else{
			return null;
		}
		
	}

	public Collection<Version> getVersionByKid(Long id) {
		// TODO Auto-generated method stub
		List<Version> list=this.hibernateTemplate.find("from Version where kid=?",id);
		return list;
	}

}
