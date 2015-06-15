package surveypark.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DataUtil {
 public static String md5(String str){
	 StringBuffer stringBuffer=new StringBuffer();
	 char[] chars={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
	 MessageDigest md;
	try {
		md = MessageDigest.getInstance("md5");
		 byte[] bytes=md.digest(str.getBytes());
		 for(byte b:bytes){
			 //高四位
			 stringBuffer.append(chars[(b>>4) & 0x0f]);
			 //低四位
			 stringBuffer.append(chars[b&0x0f]);
		 }
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}
	
	 return stringBuffer.toString();
 }
 
 public static Serializable deeplyCopy(Serializable ser){
	
	try {
		 ByteArrayOutputStream baos=new ByteArrayOutputStream();
		 ObjectOutputStream oos=new ObjectOutputStream(baos);
		 oos.writeObject(ser);
		 oos.close();
		 baos.close();
		 byte[] data=baos.toByteArray();
		 ByteArrayInputStream bais=new ByteArrayInputStream(data);
		 ObjectInputStream ois;
		 ois = new ObjectInputStream(bais);
		 Serializable copy=(Serializable) ois.readObject();
		 ois.close();
		 bais.close();
		 return copy;
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	return null;
 }
}
