package OAproject.ServiceImpl;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import OAproject.Dao.PostDao;
import OAproject.Domain.Post;
import OAproject.Service.PostService;
@Service("postService")
public class PostServiceImpl implements PostService<Post>{
	@Resource(name="postDao")
	private PostDao postDao;
	public Collection<Post> getAllPostsByIds(Long[] pids) {
		// TODO Auto-generated method stub
	
		
		return this.postDao.getAllPostsByIds(pids);
	}
	
	public void saveEntry(Post t) {
		// TODO Auto-generated method stub
		this.postDao.saveEntry(t);
	}
	public void updateEntry(Post t) {
		// TODO Auto-generated method stub
		this.postDao.updateEntry(t);
	}
	public void deleteEntryById(Serializable id) {
		// TODO Auto-generated method stub
		this.postDao.deleteEntryById(id);
	}
	public Post getEntryById(Serializable id) {
		// TODO Auto-generated method stub
		return (Post) this.postDao.getDEntryById(id);
	}
	public Collection<Post> getAllEntry() {
		// TODO Auto-generated method stub
		return this.postDao.getAllEntry();
	}

}
