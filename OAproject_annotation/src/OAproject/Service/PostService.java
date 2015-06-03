package OAproject.Service;

import java.util.Collection;

import OAproject.Domain.Post;
import OAproject.Service.Base.BaseService;

public interface PostService<T> extends BaseService<T> {
	public Collection<Post> getAllPostsByIds(Long[] pids);
}
