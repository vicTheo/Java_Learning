package OAproject.ServiceImpl;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import OAproject.Dao.KynamicDao;
import OAproject.Domain.Kynamic;
import OAproject.Service.KynamicService;
@Service("kynamicService")
public class KynamicServiceImpl implements KynamicService<Kynamic> {
    @Resource(name="kynamicDao")
	private KynamicDao kynamicDao;
	
	public void saveEntry(Kynamic t) {
		// TODO Auto-generated method stub
		this.kynamicDao.saveEntry(t);
	}

	public void updateEntry(Kynamic t) {
		// TODO Auto-generated method stub
		
	}

	public void deleteEntryById(Serializable id) {
		// TODO Auto-generated method stub
		
	}

	public Kynamic getEntryById(Serializable id) {
		// TODO Auto-generated method stub
		return (Kynamic) this.kynamicDao.getAllEntry();
	}

	public Collection<Kynamic> getAllEntry() {
		// TODO Auto-generated method stub
		return this.kynamicDao.getAllEntry();
	}

	public Kynamic getByName(String name) {
		// TODO Auto-generated method stub
		return this.kynamicDao.getByName(name);
	}

}
