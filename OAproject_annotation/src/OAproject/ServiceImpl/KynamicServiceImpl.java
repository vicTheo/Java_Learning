package OAproject.ServiceImpl;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import OAproject.Dao.KynamicDao;
import OAproject.Domain.Kynamic;
import OAproject.Domain.Version;
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
		this.kynamicDao.updateEntry(t);
	}

	public void deleteEntryById(Serializable id) {
		// TODO Auto-generated method stub
		this.kynamicDao.deleteEntryById(id);
	}

	public Kynamic getEntryById(Serializable id) {
		// TODO Auto-generated method stub
		return (Kynamic) this.kynamicDao.getDEntryById(id);
	}

	public Collection<Kynamic> getAllEntry() {
		// TODO Auto-generated method stub
		return this.kynamicDao.getAllEntry();
	}

	public Kynamic getByName(String name) {
		// TODO Auto-generated method stub
		return this.kynamicDao.getByName(name);
	}

	public Collection<Kynamic> getSibLingsById(Long id) {
		// TODO Auto-generated method stub
		return this.kynamicDao.getSibLingsById(id);
	}

	public Kynamic getParentNode(Long id) {
		// TODO Auto-generated method stub
		return (Kynamic) this.kynamicDao.getParentNode(id);
	}

	public Collection<Version> getVersionByKid(Long id) {
		// TODO Auto-generated method stub
		return this.kynamicDao.getVersionByKid(id);
	}

}
