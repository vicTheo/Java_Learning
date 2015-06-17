package surveypark.domain;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;

public abstract class BaseEntity implements Serializable{
private static final long serialVersionUID = -6155528060215446412L;


public abstract Integer getId();


public abstract void setId(Integer id);

public String toString(){
	String className=this.getClass().getSimpleName();
	Field[] fields=this.getClass().getDeclaredFields();
	StringBuffer stringBuffer=new StringBuffer();
	stringBuffer.append(className+":{");
	
			try {
				Class fType=null;
				Object fValue=null;
				for(Field f:fields){
					fType=f.getType();
					if(fType.isPrimitive()
					   ||fType==String.class
					   ||fType==Date.class
					   ||fType==Integer.class
					   ||fType==Long.class
					   &&!Modifier.isStatic(f.getModifiers())){
						f.setAccessible(true);
						fValue=f.get(this);
						stringBuffer.append(f.getName()+":"+fValue+",");
					}
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		  String str=stringBuffer.toString();
		  str=str.substring(0,str.lastIndexOf(","));
		  str=stringBuffer.append(str+"}").toString();
	     return str;
}
}
