package surveypark.utils;

import java.util.List;

public class ValidateUtil {
 public static boolean isValid(String[] values){
	 if(values==null||values.length==0){
		 return false;
	 }
	 return true;
 }
 public static boolean isValid(List list){
	 if(list==null||list.isEmpty()){
		 return true;
	 }
	 return false;
 }
public static boolean isValid(String str) {
	 if(str==null||"".equals(str.trim())){
		 return false;
	 }
	
	return true;
}
}
