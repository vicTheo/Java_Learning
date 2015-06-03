package OAproject.Dao;

import java.util.Collection;

import OAproject.Dao.BaseDao.BaseDao;

public interface UserDao<T> extends BaseDao<T> {
  public Collection<T> getAllUsers();
  public T getUserByName(String username);
}
