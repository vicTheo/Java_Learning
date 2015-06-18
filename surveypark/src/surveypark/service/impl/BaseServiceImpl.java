package surveypark.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import surveypark.dao.BaseDao;
import surveypark.service.BaseService;

public class BaseServiceImpl<T> implements BaseService<T> {
    
	public BaseDao dao;
	private Class classt;
	public BaseServiceImpl(){
		ParameterizedType type=(ParameterizedType) this.getClass().getGenericSuperclass();
	    this.classt=(Class) type.getActualTypeArguments()[0];
	}
	public BaseDao getDao() {
		return dao;
	}
	@Resource
	public void setDao(BaseDao<T> dao) {
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
	public List<T> getAllEntities() {
		 String hql="from "+classt.getSimpleName();
		 
		return this.dao.findEntityByHql(hql);
	}
    
     //单值检索
	public Object uniqueResult(String hql,Object...objects){
		return dao.uniqueResult(hql, objects);
	}
	//操作原生sql
	public void executeSql(String sql, Object... objects) {
		this.dao.executeSql(sql, objects);
		
	}
	public List<T> findObjectsBySql(String sql, Object... objects) {
		
		return this.dao.findObjectsBySql(sql, objects);
	}
}
