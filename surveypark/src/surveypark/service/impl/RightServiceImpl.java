package surveypark.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import surveypark.dao.BaseDao;
import surveypark.domain.security.Right;
import surveypark.service.RightService;
import surveypark.utils.DataUtil;
import surveypark.utils.StringUtil;
import surveypark.utils.ValidateUtil;
@Service("rightService")
public class RightServiceImpl extends BaseServiceImpl<Right> implements
		RightService {

	@Resource(name="rightDao")
	public void setDao(BaseDao<Right> dao) {
		super.setDao(dao);
	}

	@Override
	public void saveOrUpdateEntity(Right model) {
		//insert
		int rightpos=0;
		long rightcode=1;
		if(model.getId()==null){
			//查询最大权限位上的最大权限码
			String hql="select max(r.rightPos),max(r.rightCode) from Right r where r.rightPos=(select max(rr.rightPos) from Right rr)";
		    Object[] objects=(Object[]) this.dao.uniqueResult(hql);
		    Integer toprightpos=(Integer) objects[0];
		    Long toprightcode=(Long) objects[1];
		    if(toprightpos==null){
		    	rightpos=0;
		    	rightcode=1;
		    }else{
		    	if(toprightcode>=(1L<<60)){
		    		rightpos=toprightpos+1;
		    		rightcode=1;
		    	}else{
		    		rightpos=toprightpos;
		    		rightcode=toprightcode<<1;
		    	}
		    }
		    model.setRightPos(rightpos);
		    model.setRightCode(rightcode);
		}
		super.saveOrUpdateEntity(model);
	}

	public void appendRightsUrl(String url) {
		String hql="select count(*) from Right r where r.rightUrl=?";
		Long count=(Long) this.dao.uniqueResult(hql, url);
		if(count==0){
			Right right=new Right();
			right.setRightUrl(url);
			this.saveOrUpdateEntity(right);
		}
	}
     //查询在指定范围的权限
	public List<Right> findRightsInRange(Integer[] ownRightIds) {
		if(ValidateUtil.isValid(ownRightIds)){
			String hql="from Right r where r.id in("+StringUtil.arr2string(ownRightIds)+")";
			return this.findEntityByHql(hql);
		}
		return null;
	}
      //查询不在指定范围的权限
	public List<Right> findRightsNotInRange(Set<Right> rights) {
		if(ValidateUtil.isValid(rights)){
			return this.getAllEntities();
		}
		String hql="from Right r where r.id not in("+DataUtil.extractEntitiesIds(rights)+")";
		
		return this.findEntityByHql(hql);
	}
     //获取最大权限位
	public int getMaxRightPos() {
		String hql="select max(r.rightPos) from Right r";
		Integer i=(Integer) this.uniqueResult(hql);
		return i==null?0:i;
	}
     //批量更新权限
	public void batchUpdateRights(List<Right> allRights) {
        if(!ValidateUtil.isValid(allRights)){
        	
        		String hql="update Right r set r.rightName=?,r.rightDesc=?,r.common=? where r.id=?";
        		for(Right r:allRights){
        		 this.batchEntityByHql(hql, r.getRightName(),r.getRightDesc(),r.isCommon(),r.getId());
        	}
        
        }		
	
	}
     
     
}
