package OAproject.DaoImpl;

import org.springframework.stereotype.Repository;

import OAproject.Dao.FormTemplateDao;
import OAproject.DaoImpl.BaseDaoImpl.BaseDaoImpl;
import OAproject.Domain.FormTemplate;
@Repository("formTemplateDao")
public class FormTemplateDaoImpl extends BaseDaoImpl<FormTemplate> implements FormTemplateDao{

}
