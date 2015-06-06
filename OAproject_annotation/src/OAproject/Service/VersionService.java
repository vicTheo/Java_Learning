package OAproject.Service;

import OAproject.Service.Base.BaseService;

public interface VersionService<T> extends BaseService<T>{
	  public Long getMaxVersionId(Long kid);
}
