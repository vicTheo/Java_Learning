package surveypark.service;

import java.util.List;
import java.util.Set;

import surveypark.domain.security.Right;

public interface RightService extends BaseService<Right>{
  public void appendRightsUrl(String url);
//查询指定范围的权限
public List<Right> findRightsInRange(Integer[] ownRightIds);
//查询不在指定范围的权限
public List<Right> findRightsNotInRange(Set<Right> rights);
public int getMaxRightPos();
public void batchUpdateRights(List<Right> allRights);
}
