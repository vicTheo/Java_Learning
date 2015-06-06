package OAproject.DaoImpl;



import java.util.List;

import org.springframework.stereotype.Repository;



import OAproject.Dao.VersionDao;
import OAproject.DaoImpl.BaseDaoImpl.BaseDaoImpl;
import OAproject.Domain.Version;
@Repository("versionDao")
public class VersionDaoImpl extends BaseDaoImpl<Version> implements VersionDao<Version>{

	public Long getMaxVersionId(Long kid) {
		// TODO Auto-generated method stub
		List<Long> versionlist=this.hibernateTemplate.find("select max(version) from Version where kid=?",kid);
		return versionlist.get(0);
		
	}

}
