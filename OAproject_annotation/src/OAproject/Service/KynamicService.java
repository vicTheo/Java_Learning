package OAproject.Service;

import java.util.Collection;

import OAproject.Domain.Kynamic;
import OAproject.Domain.Version;
import OAproject.Service.Base.BaseService;

public interface KynamicService <T> extends BaseService<T>{
public Kynamic getByName(String name);
public Collection<T> getSibLingsById(Long id);
public T getParentNode(Long id);
public Collection<Version> getVersionByKid(Long id);
}
