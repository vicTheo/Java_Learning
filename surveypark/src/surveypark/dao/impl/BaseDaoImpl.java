package surveypark.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import surveypark.dao.BaseDao;

public class BaseDaoImpl<T> implements BaseDao<T> {
	@Resource(name="sessionFactory")
    private SessionFactory sessionFactory;
	private Class classt;
	public BaseDaoImpl(){
		ParameterizedType type=(ParameterizedType) this.getClass().getGenericSuperclass();
	    this.classt=(Class) type.getActualTypeArguments()[0];
	}
	public void saveOrUpdateEntity(T t) {
      sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	public void deleteEntity(T t) {
     sessionFactory.getCurrentSession().delete(t);
	}

	public void batchEntityByHql(String hql, Object... objects) {
        Query q=sessionFactory.getCurrentSession().createQuery(hql);
        for(int i=0;i<objects.length;i++){
        	q.setParameter(i, objects[i]);
        }
        q.executeUpdate();
	}

	public T getEntity(Integer id) {
		
		return (T) sessionFactory.getCurrentSession().get(classt, id);
	}

	public T loadEntity(Integer id) {
		return (T) sessionFactory.getCurrentSession().load(classt, id);
	}

	public List<T> findEntityByHql(String hql, Object... objects) {
		Query q=sessionFactory.getCurrentSession().createQuery(hql);
		for(int i=0;i<objects.length;i++){
			q.setParameter(i, objects[i]);
		}
		return q.list();
	}

	public void saveEntity(T t) {
      sessionFactory.getCurrentSession().save(t);		
	}

	public void updateEntity(T t) {
       sessionFactory.getCurrentSession().update(t);		
	}
	public Object uniqueResult(String hql, Object... objects) {
		Query q=sessionFactory.getCurrentSession().createQuery(hql);
		for(int i=0;i<objects.length;i++){
			q.setParameter(i, objects[i]);
		}
		return q.uniqueResult();
	}

}
