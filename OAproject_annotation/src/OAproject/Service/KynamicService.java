package OAproject.Service;

import OAproject.Domain.Kynamic;
import OAproject.Service.Base.BaseService;

public interface KynamicService <T> extends BaseService<T>{
public Kynamic getByName(String name);
}
