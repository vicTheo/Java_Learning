package OAproject.ServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;

import OAproject.Dao.FormTemplateDao;
import OAproject.Domain.FormTemplate;
import OAproject.Service.FormTemplateService;
@Service("formTemplateService")
public class FormTemplateServiceImpl implements FormTemplateService{
    @Resource(name="formTemplateDao")
	private FormTemplateDao formTemplateDao;
	public void saveEntry(FormTemplate t) {
		// TODO Auto-generated method stub
		this.formTemplateDao.saveEntry(t);
	}

	public void updateEntry(FormTemplate t) {
		// TODO Auto-generated method stub
		
	}

	public void deleteEntryById(Serializable id) {
		// TODO Auto-generated method stub
		
	}

	public FormTemplate getEntryById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<FormTemplate> getAllEntry() {
		// TODO Auto-generated method stub
		
		return this.formTemplateDao.getAllEntry();
	}

	public InputStream download(Long id) throws Exception {
		// TODO Auto-generated method stub
		FormTemplate formTemplate=this.formTemplateDao.getDEntryById(id);
//		String filename=new String(formTemplate.getName().getBytes("utf-8"),"ISO8859-1");
		String filename=URLEncoder.encode(formTemplate.getName(),"UTF-8");
		ActionContext.getContext().put("filename",filename);
		InputStream inputStream=new FileInputStream(new File(formTemplate.getUrl()));
		return inputStream;
	}

}
