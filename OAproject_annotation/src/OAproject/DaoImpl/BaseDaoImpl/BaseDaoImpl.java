package OAproject.DaoImpl.BaseDaoImpl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import OAproject.Dao.BaseDao.BaseDao;


public class BaseDaoImpl<T> implements BaseDao<T>{
   
	@Resource(name="hibernateTemplate")
	public HibernateTemplate hibernateTemplate;
    private Class classt;
  
	/**
	 * 1、在父类中要执行一段代码，这个代码的执行时间为子类创建对象的时候，这段代码已经执行完了，根据这个需求，有两种方案供选择
	 *      *  利用static语句块
	 *      *  利用父类的构造函数
	 * 2、分析：
	 *      因为得到ParameterizedType需要用到this关键字，而this关键字不能出现在static语句块中
	 *    所以只能选择父类的构造器
	 */
	public BaseDaoImpl(){
		 ParameterizedType type=(ParameterizedType) this.getClass().getGenericSuperclass();
		  this.classt=(Class) type.getActualTypeArguments()[0];
		 
	}
	
	public void saveEntry(T t) {
		this.hibernateTemplate.save(t);
	}

	public void updateEntry(T t) {
		// TODO Auto-generated method stub
		this.hibernateTemplate.update(t);
	}

	public void deleteEntryById(Serializable id) {
		// TODO Auto-generated method stub
		T t=this.getDEntryById(id);
		this.hibernateTemplate.delete(t);
	}

	public T getDEntryById(Serializable id) {
		// TODO Auto-generated method stub
		return (T) this.hibernateTemplate.get(this.classt, id);
	}

	public Collection<T> getAllEntry() {
		// TODO Auto-generated method stub
		
		return  this.hibernateTemplate.find("from "+this.classt.getName());
		
		
	}

}
