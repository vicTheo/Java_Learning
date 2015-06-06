package OAproject.Dao;

import java.util.Collection;

import OAproject.Dao.BaseDao.BaseDao;
import OAproject.Domain.Kynamic;
import OAproject.Domain.Version;

public interface KynamicDao<T> extends BaseDao<T>{
public Kynamic getByName(String name);
public Collection<T> getSibLingsById(Long id);
public T getParentNode(Long id);
public Collection<Version> getVersionByKid(Long id);
}
