package surveypark.service.impl;

import java.util.List;

import javax.annotation.Resource;

import surveypark.dao.BaseDao;
import surveypark.service.BaseService;

public class BaseServiceImpl<T> implements BaseService<T> {
    
	public BaseDao dao;
	
	public BaseDao getDao() {
		return dao;
	}
	@Resource
	public void setDao(BaseDao dao) {
		this.dao = dao;
	}

	public void saveEntity(T t) {
      this.dao.saveEntity(t);		
	}

	public void updateEntity(T t) {
       this.dao.updateEntity(t);		
	}

	public void saveOrUpdateEntity(T t) {
       this.dao.saveOrUpdateEntity(t);		
	}

	public void deleteEntity(T t) {
      this.dao.deleteEntity(t);		
	}

	public void batchEntityByHql(String hql, Object... objects) {
      this.dao.batchEntityByHql(hql, objects);		
	}

	public T getEntity(Integer id) {
		return (T) this.dao.getEntity(id);
	}

	public T loadEntity(Integer id) {
		return (T) this.dao.loadEntity(id);
	}

	public List<T> findEntityByHql(String hql, Object... objects) {
		return this.dao.findEntityByHql(hql, objects);
	}

}
