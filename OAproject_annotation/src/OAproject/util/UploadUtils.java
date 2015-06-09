package OAproject.util;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

public class UploadUtils {
	
	public static String saveUploadFile(File upload){
		//�����ڸ�ʽ�����ַ�����һ�������� 
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
		/*
		 * �õ�upload�ļ��еľ���·��
		 * ServletActionContext.getServletContext()
		 * =
		 * C:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\oaproject\WEB-INF/upload/2012\02\16\aaaaadfasdf
		 */
		String basePath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload");
		//���������͸�ʽ��Ϊ"/yyyy/MM/dd/"������ʽ���ַ���
		String subPath = sdf.format(new Date());
		//����ļ��в����ڣ��ʹ����ļ���
		File dir = new File(basePath+subPath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		//String path = basePath+"/"+this.uploadFileName;
		//UUID.randomUUID().toString()�ܹ���֤���ֵ�Ψһ��
		String path = basePath+subPath+UUID.randomUUID().toString();
		File dest = new File(path);
		//���ļ��ƶ���dest��
		upload.renameTo(dest);
		return path;
	}
}
