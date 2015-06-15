package surveypark.action;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public abstract class  BaseAction<T> extends ActionSupport implements ModelDriven<T>,
	
    
    Preparable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8335104612326319625L;
	public T model;
    @SuppressWarnings("unchecked")
	public BaseAction(){
    	ParameterizedType type=(ParameterizedType) this.getClass().getGenericSuperclass();
    	@SuppressWarnings("rawtypes")
		Class classt=(Class) type.getActualTypeArguments()[0];
    	try {
			model=(T) classt.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}

	public  T getModel(){
		return model;
	};
	

}
