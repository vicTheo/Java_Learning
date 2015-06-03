package OAproject.Service.Base;

import java.io.Serializable;
import java.util.Collection;

public interface BaseService<T> {
	 public void saveEntry(T t);
	 public void updateEntry(T t);
	 public void deleteEntryById(Serializable id);
	 public T getEntryById(Serializable id);
	 public Collection<T> getAllEntry();
}
