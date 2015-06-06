package OAproject.Dao;

import OAproject.Dao.BaseDao.BaseDao;

public interface VersionDao<T> extends BaseDao<T>{
  public Long getMaxVersionId(Long kid);
}
