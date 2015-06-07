package OAproject.ServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.jbpm.pvm.internal.builder.ProcessDefinitionBuilder;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.springframework.stereotype.Service;

import OAproject.Service.PDManagerService;
@Service("pdmanagerService")
public class PDManagerServiceImpl implements PDManagerService{
    @Resource(name="processEngine")
    private ProcessEngine processEngine;
	public Collection<ProcessDefinition> getVersionList() {
		// TODO Auto-generated method stub
		List<ProcessDefinition> pdlist=this.processEngine.getRepositoryService()
		.createProcessDefinitionQuery().orderAsc(ProcessDefinitionQuery.PROPERTY_VERSION)
		.list();
		Map<String,ProcessDefinition> map=new HashMap<String,ProcessDefinition>();
		for(ProcessDefinition processDefinition:pdlist){
			map.put(processDefinition.getKey(), processDefinition);
		}
		return map.values();
	}
	//部署工作流
	public void deploy(File file) throws Exception {
		// TODO Auto-generated method stub
		InputStream inputStream=new FileInputStream(file);
		ZipInputStream zipInputStream=new ZipInputStream(inputStream);
		this.processEngine.getRepositoryService()
		.createDeployment().addResourcesFromZipInputStream(zipInputStream)
		.deploy();
	}
	public void delete(String key) {
		// TODO Auto-generated method stub
		List<ProcessDefinition> pdlist=processEngine.getRepositoryService()
		.createProcessDefinitionQuery()
		.processDefinitionKey(key).list();
		for(ProcessDefinition processDefinition:pdlist){
			this.processEngine.getRepositoryService().deleteDeploymentCascade(processDefinition.getDeploymentId());
		}
	}
	public InputStream showProcessImage(String did) throws Exception {
		// TODO Auto-generated method stub
		String filename=processEngine.getRepositoryService()
		.createProcessDefinitionQuery().deploymentId(did).uniqueResult().getImageResourceName();
		
		return processEngine.getRepositoryService()
		.getResourceAsStream(did,filename);
		
	}

}
