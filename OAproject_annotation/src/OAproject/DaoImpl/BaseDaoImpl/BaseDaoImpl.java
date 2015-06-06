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
	 * 1���ڸ�����Ҫִ��һ�δ��룬��������ִ��ʱ��Ϊ���ഴ�������ʱ����δ����Ѿ�ִ�����ˣ�����������������ַ�����ѡ��
	 *      *  ����static����
	 *      *  ���ø���Ĺ��캯��
	 * 2��������
	 *      ��Ϊ�õ�ParameterizedType��Ҫ�õ�this�ؼ��֣���this�ؼ��ֲ��ܳ�����static������
	 *    ����ֻ��ѡ����Ĺ�����
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
