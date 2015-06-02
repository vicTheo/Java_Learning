package Dao.BaseDao;

import java.io.Serializable;
import java.util.Collection;

public interface BaseDao<T> {
	 public void saveEntry(T t);
	 public void updateEntry(T t);
	 public void deleteEntryById(Serializable id);
	 public T getDEntryById(Serializable id);
	 public Collection<T> getAllEntry();
}
