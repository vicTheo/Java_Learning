package OAproject.DaoImpl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Repository;

import OAproject.Dao.PostDao;
import OAproject.DaoImpl.BaseDaoImpl.BaseDaoImpl;
import OAproject.Domain.Post;

@Repository("postDao")
public class PostDaoImpl extends BaseDaoImpl<Post> implements PostDao<Post>{

	public Collection<Post> getAllPostsByIds(Long[] pids) {
		StringBuilder sb=new StringBuilder();
		sb.append("from Post where pid in ");
		
		for(int i=0;i<pids.length;i++){
			if(i<pids.length-1){
				sb.append(pids[i]+",");
			}else{
				sb.append(pids[i]);
			}
		}
		List<Post> postList=this.hibernateTemplate.find(sb.toString());
		return new HashSet<Post>(postList);
	}
     
}
