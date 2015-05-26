package cn.geekbook.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;




public class Md5Utils {
	public String string2md5(String str){
		MessageDigest md=null;
		try {
			md=MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
		
		char[] charArray=str.toCharArray();
		byte[] byteArray=new byte[(charArray.length)];
		for(int i=0;i<byteArray.length;i++){
			byteArray[i]=(byte)charArray[i];
		}
		byte[] md5bytes=md.digest(byteArray);
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<md5bytes.length;i++){
			int val=((int)md5bytes[i])&0xff;
			if(val<16){
				sb.append(0);
			}
			sb.append(Integer.toHexString(val));
		}
		return sb.toString();
		
	} 
	public String convertMd5(String str){
		char[] charArray=str.toCharArray();
		for(int i=0;i<charArray.length;i++){
			charArray[i]=(char) (charArray[i]^'t');
		}
		
		return new String(charArray);
	}
  
}
