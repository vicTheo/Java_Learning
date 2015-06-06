package OAproject.Dao;

import OAproject.Dao.BaseDao.BaseDao;
import OAproject.Domain.Kynamic;

public interface KynamicDao<T> extends BaseDao<T>{
public Kynamic getByName(String name);
}
