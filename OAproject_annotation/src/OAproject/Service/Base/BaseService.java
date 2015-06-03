package OAproject.Service.Base;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

public interface BaseService<T> {
	@Transactional(readOnly=false)
	 public void saveEntry(T t);
	@Transactional(readOnly=false)
	 public void updateEntry(T t);
	@Transactional(readOnly=false)
	 public void deleteEntryById(Serializable id);
	 public T getEntryById(Serializable id);
	 public Collection<T> getAllEntry();
}
