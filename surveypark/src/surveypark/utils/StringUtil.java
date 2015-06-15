package surveypark.utils;

public class StringUtil {
 public static String[] string2arr(String str,String tag){
	 if(ValidateUtil.isValid(str)){
		 return str.split(tag);
	 }
	
	 return null;
 }

public static boolean contains(String[] values, String value) {
	if(ValidateUtil.isValid(values)){
		for(String str:values){
			if(str.equals(value)){
				return true;
			}
		}
	}
	return false;
}

public static String arr2string(String[] value) {
   String temp="";
   if(ValidateUtil.isValid(value)){
	for(String str:value){
	   temp+=str+",";
   }
	return temp.substring(0,temp.length()-1);
   }
	return null;
}
}
