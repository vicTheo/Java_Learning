package OAproject.ServiceImpl;

import java.util.Collection;

import javax.annotation.Resource;

import OAproject.Dao.PostDao;
import OAproject.Domain.Post;
import OAproject.Service.PostService;
import OAproject.ServiceImpl.Base.BaseServiceImpl;

public class PostServiceImpl extends BaseServiceImpl<Post> implements PostService<Post>{
	@Resource(name="postDao")
	private PostDao postDao;
	public Collection<Post> getAllPostsByIds(Long[] pids) {
		// TODO Auto-generated method stub
	
		
		return this.postDao.getAllPostsByIds(pids);
	}

}
