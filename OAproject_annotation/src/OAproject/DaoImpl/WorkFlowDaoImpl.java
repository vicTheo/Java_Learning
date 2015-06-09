package OAproject.DaoImpl;

import org.springframework.stereotype.Repository;

import OAproject.Dao.WorkFlowDao;
import OAproject.DaoImpl.BaseDaoImpl.BaseDaoImpl;
import OAproject.Domain.Form;
@Repository("workflowDao")
public class WorkFlowDaoImpl  extends BaseDaoImpl<Form> implements WorkFlowDao<Form>{

}
