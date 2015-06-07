package pd;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.jbpm.api.Configuration;
import org.jbpm.api.Deployment;
import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.jbpm.pvm.internal.processengine.ProcessEngineImpl;

import org.junit.Test;

import utils.BaseJbpm;

/**
 * 流程定义管理 * 流程定义 * 把流程定义文档部署到jbpm中 * 查询 * 查询流程部署 * 查询所有的流程部署 * 根据部署ID查询流程部署 *
 * 查询流程定义 * 查询所有的流程定义 * 根据deploymentID查询流程定义 * 根据pdid查询流程定义 * 根据pdkey查询流程定义 * 删除
 * * 查看流程图
 */
public class PDManager extends BaseJbpm {
	/**
	 * 涉及到的表 * JBPM4_DEPLOYMENT * 部署表，用来描述一次部署 * 字段说明 DBID_： 主键、部署ID STATE: 状态
	 * active * JBPM4_LOB * 仓库表 存放了流程定义文档(xml,png) * 字段说明 DEPLOYMENT_:部署ID 外键
	 * NAME_: xml或者png的文件路径 * JBPM4_DEPLOYPROP * 部署属性表 * 字段 DBID_:主键
	 * OBJNAME_:流程定义名称 KEY_: * 每部署一次，生成4行记录 langid 语言版本 jpdl-4.4 pdid
	 * {pdkey-version} pdkey 流程定义名称 一般情况下,pdkey和objname_的值是一样的 pdversion 版本号
	 * 如果pdkey没有发生改变，每部署一次，版本号加1 如果pdkey发生改变，则是一个全新的名称，所以版本号应该从1开始计算
	 */

	// 从classpath部署
	@Test
	public void testDeployFromClasspath() {
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		// NewDeployment newDeployment=repositoryService.createDeployment();
		// newDeployment.addResourceFromClasspath("");
		// newDeployment.addResourceFromClasspath("");
		// newDeployment.deploy();

		repositoryService.createDeployment()
				.addResourceFromClasspath("qingjia.jpdl.xml")
				.addResourceFromClasspath("qingjia.png").deploy();
	}

	// 从inputstream部署
	@Test
	public void testDeployByInputStream() {
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream("qingjia.jpdl.xml");
		processEngine.getRepositoryService().createDeployment()
				.addResourceFromInputStream("qingjia.jpdl.xml", inputStream)
				.deploy();
	}

	// 从zipinputstream部署
	@Test
	public void testDeployByZipInputStream() {
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream("qingjia.zip");
		ZipInputStream zipInputStream = new ZipInputStream(inputStream);
		processEngine.getRepositoryService().createDeployment()
				.addResourcesFromZipInputStream(zipInputStream).deploy();
	}
   //查看所有的部署
	@Test
	public void testDeplymentQuery(){
		List<Deployment> deploymentList=processEngine.getRepositoryService()
		.createDeploymentQuery().list();
		for(Deployment deployment:deploymentList){
			System.out.println(deployment.getId()+","+deployment.getState());
		}
	}
	//根据id查看部署
	@Test
	public void testDeplymentQueryById(){
		Deployment deployment=processEngine.getRepositoryService()
		.createDeploymentQuery().deploymentId("1").uniqueResult();
		System.out.println(deployment.getId()+","+deployment.getState());
	}
	//查看所有的流程定义
	@Test
	public void testProcessDefinitionQuery(){
		List<ProcessDefinition> pdList=processEngine.getRepositoryService().createProcessDefinitionQuery()
		.list();
		for(ProcessDefinition processDefinition:pdList){
			System.out.print(processDefinition.getDeploymentId()+",");
			System.out.print(processDefinition.getKey()+",");
			System.out.print(processDefinition.getId()+",");
			System.out.print(processDefinition.getName()+",");
			System.out.print(processDefinition.getVersion()+",");
			System.out.println();
		}
		
	}
	//根据pdid查看流程定义
	@Test
	public void testProcessDefinitionQueryById(){
		 ProcessDefinition processDefinition=processEngine.getRepositoryService().createProcessDefinitionQuery()
		  .processDefinitionId("qingjia1-2").uniqueResult();
		 System.out.print(processDefinition.getDeploymentId()+",");
			System.out.print(processDefinition.getKey()+",");
			System.out.print(processDefinition.getId()+",");
			System.out.print(processDefinition.getName()+",");
			System.out.print(processDefinition.getVersion()+",");
			
	}
	//根据pdkey查询流程定义
	@Test
	public void testProcessDefinitionQueryByKey(){
		List<ProcessDefinition> processDefinitions=processEngine.getRepositoryService().createProcessDefinitionQuery()
		.processDefinitionKey("qingjia1").list();
		 for(ProcessDefinition processDefinition:processDefinitions){
			System.out.print(processDefinition.getDeploymentId()+",");
			System.out.print(processDefinition.getKey()+",");
			System.out.print(processDefinition.getId()+",");
			System.out.print(processDefinition.getName()+",");
			System.out.print(processDefinition.getVersion()+",");
			System.out.println();
		}
	}
	//根据name查询流程定义
	
	//根据name模糊查询流程定义
	
	/*
	 * 只能删除流程部署
	 *   没有提供删除流程定义的api
	 */
	//删除流程部署
	@Test
	public void testDeleteDeployment(){
//		processEngine.getRepositoryService().deleteDeployment("20001");
		processEngine.getRepositoryService().deleteDeploymentCascade("30001");
	}
	
	/*
	 * 根据key获取到所有的流程定义，遍历，得到流程部署，然后一依次次删除
	 */
	
	//查询流程图
	@Test
	public void testShowImg() throws Exception{
		InputStream inputStream=processEngine.getRepositoryService().getResourceAsStream("30001", "qingjia.png");
		OutputStream ot=new FileOutputStream("d:/qingjia.png");
		for(int b=-1;(b=inputStream.read())!=-1;){
			ot.write(b);
		}
		inputStream.close();
		ot.close();
	}
}
