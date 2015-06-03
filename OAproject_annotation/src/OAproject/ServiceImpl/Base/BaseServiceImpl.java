package OAproject.ServiceImpl.Base;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import OAproject.DaoImpl.BaseDaoImpl.BaseDaoImpl;
import OAproject.Service.Base.BaseService;

    public class BaseServiceImpl<T> implements BaseService<T>{
    
   
    public BaseDaoImpl<T> baseDao;
   
	public BaseDaoImpl<T> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDaoImpl<T> baseDao) {
		this.baseDao = baseDao;
	}

	 @Transactional(readOnly=false)
	public void saveEntry(T t) {
		// TODO Auto-generated method stub
		this.baseDao.saveEntry(t);
	}
	 @Transactional(readOnly=false)
	public void updateEntry(T t) {
		// TODO Auto-generated method stub
		this.baseDao.updateEntry(t);
	}
	 @Transactional(readOnly=false)
	public void deleteEntryById(Serializable id) {
		// TODO Auto-generated method stub
		this.baseDao.deleteEntryById(id);
	}

	public T getEntryById(Serializable id) {
		// TODO Auto-generated method stub
		
		return this.baseDao.getDEntryById(id);
	}

	public Collection<T> getAllEntry() {
		// TODO Auto-generated method stub
		
		return this.baseDao.getAllEntry();
	}

}
