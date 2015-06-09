package OAproject.Service;

import java.io.InputStream;

import OAproject.Domain.FormTemplate;
import OAproject.Service.Base.BaseService;

public interface FormTemplateService extends BaseService<FormTemplate>{
public InputStream download(Long id)throws Exception;
}
