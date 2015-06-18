package surveypark.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import surveypark.domain.log.Log;
import surveypark.service.LogService;
@Controller("logAction")
@Scope("prototype")
public class LogAction extends BaseAction<Log> {

	private static final long serialVersionUID = 7207406436423395605L;
    @Resource(name="logService")
    private LogService logService;
    private List<Log> allLogs;
    
    public List<Log> getAllLogs() {
		return allLogs;
	}

	public void setAllLogs(List<Log> allLogs) {
		this.allLogs = allLogs;
	}

	public String findAllLogs(){
    	this.allLogs=logService.findNearestLogs();
    	return "toLogListPage";
    }
}   
