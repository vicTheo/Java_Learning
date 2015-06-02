package Action.Base;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> implements ModelDriven<T>{
    private Class classt;
    private T t;
    
    public BaseAction(){
    	ParameterizedType type=(ParameterizedType) this.getClass().getGenericSuperclass();
    	this.classt=(Class) type.getActualTypeArguments()[0];
    	
    	try {
			this.t=(T) this.classt.newInstance();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
    public static final String LISTACTION="listAction";
    public static final String ADDUI="addUI";
    public static final String UPDATEUI="updateUI";
    public static final String ACTION2ACTION="action2action";
    public String listAction = LISTACTION;
    public String updateUI = UPDATEUI;
    public String addUI = ADDUI;
    public String action2action = ACTION2ACTION;
	
    public T getModel() {
		// TODO Auto-generated method stub
		return this.t;
	}

}
