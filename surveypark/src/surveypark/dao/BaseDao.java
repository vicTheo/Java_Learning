package surveypark.dao;

import java.util.List;

public interface BaseDao<T> {
	//写操作
  public void saveEntity(T t);
  public void updateEntity(T t);
  public void saveOrUpdateEntity(T t);
  public void deleteEntity(T t);
  
  //根据hql批处理实体
  public void batchEntityByHql(String hql,Object... objects);
  
  public T getEntity(Integer id);
  public T loadEntity(Integer id);
  public List<T> findEntityByHql(String hql,Object...objects);
  public Object uniqueResult(String hql,Object... objects);
  //操作原生sql
  public void executeSql(String sql,Object...objects);
  
  public List<T> findObjectsBySql(String sql,Object...objects);
}
