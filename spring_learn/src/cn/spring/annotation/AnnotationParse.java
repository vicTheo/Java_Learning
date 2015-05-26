package cn.spring.annotation;

import java.lang.annotation.Target;
import java.lang.reflect.Method;

import org.junit.Test;

public class AnnotationParse {
@Test
public void parse(){
	Class classt=Person.class;
	if(classt.isAnnotationPresent(Name.class)){
	Name name=	(Name) classt.getAnnotation(Name.class);
	System.out.println(name.value());
	}
	Method[] methods=classt.getMethods();
	for(Method method:methods){
		if(method.isAnnotationPresent(Desription.class)){
			Desription description =method.getAnnotation(Desription.class);
			System.out.println(description.value());
		}
	}
}
}
