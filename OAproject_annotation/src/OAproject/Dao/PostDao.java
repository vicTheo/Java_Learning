package OAproject.Dao;

import java.util.Collection;

import OAproject.Dao.BaseDao.BaseDao;

public interface PostDao<T> extends BaseDao<T>{
  public Collection<T> getAllPostsByIds(Long[] pids);
}
