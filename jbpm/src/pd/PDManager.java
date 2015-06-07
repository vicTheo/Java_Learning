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
 * ���̶������ * ���̶��� * �����̶����ĵ�����jbpm�� * ��ѯ * ��ѯ���̲��� * ��ѯ���е����̲��� * ���ݲ���ID��ѯ���̲��� *
 * ��ѯ���̶��� * ��ѯ���е����̶��� * ����deploymentID��ѯ���̶��� * ����pdid��ѯ���̶��� * ����pdkey��ѯ���̶��� * ɾ��
 * * �鿴����ͼ
 */
public class PDManager extends BaseJbpm {
	/**
	 * �漰���ı� * JBPM4_DEPLOYMENT * �������������һ�β��� * �ֶ�˵�� DBID_�� ����������ID STATE: ״̬
	 * active * JBPM4_LOB * �ֿ�� ��������̶����ĵ�(xml,png) * �ֶ�˵�� DEPLOYMENT_:����ID ���
	 * NAME_: xml����png���ļ�·�� * JBPM4_DEPLOYPROP * �������Ա� * �ֶ� DBID_:����
	 * OBJNAME_:���̶������� KEY_: * ÿ����һ�Σ�����4�м�¼ langid ���԰汾 jpdl-4.4 pdid
	 * {pdkey-version} pdkey ���̶������� һ�������,pdkey��objname_��ֵ��һ���� pdversion �汾��
	 * ���pdkeyû�з����ı䣬ÿ����һ�Σ��汾�ż�1 ���pdkey�����ı䣬����һ��ȫ�µ����ƣ����԰汾��Ӧ�ô�1��ʼ����
	 */

	// ��classpath����
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

	// ��inputstream����
	@Test
	public void testDeployByInputStream() {
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream("qingjia.jpdl.xml");
		processEngine.getRepositoryService().createDeployment()
				.addResourceFromInputStream("qingjia.jpdl.xml", inputStream)
				.deploy();
	}

	// ��zipinputstream����
	@Test
	public void testDeployByZipInputStream() {
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream("qingjia.zip");
		ZipInputStream zipInputStream = new ZipInputStream(inputStream);
		processEngine.getRepositoryService().createDeployment()
				.addResourcesFromZipInputStream(zipInputStream).deploy();
	}
   //�鿴���еĲ���
	@Test
	public void testDeplymentQuery(){
		List<Deployment> deploymentList=processEngine.getRepositoryService()
		.createDeploymentQuery().list();
		for(Deployment deployment:deploymentList){
			System.out.println(deployment.getId()+","+deployment.getState());
		}
	}
	//����id�鿴����
	@Test
	public void testDeplymentQueryById(){
		Deployment deployment=processEngine.getRepositoryService()
		.createDeploymentQuery().deploymentId("1").uniqueResult();
		System.out.println(deployment.getId()+","+deployment.getState());
	}
	//�鿴���е����̶���
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
	//����pdid�鿴���̶���
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
	//����pdkey��ѯ���̶���
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
	//����name��ѯ���̶���
	
	//����nameģ����ѯ���̶���
	
	/*
	 * ֻ��ɾ�����̲���
	 *   û���ṩɾ�����̶����api
	 */
	//ɾ�����̲���
	@Test
	public void testDeleteDeployment(){
//		processEngine.getRepositoryService().deleteDeployment("20001");
		processEngine.getRepositoryService().deleteDeploymentCascade("30001");
	}
	
	/*
	 * ����key��ȡ�����е����̶��壬�������õ����̲���Ȼ��һ���δ�ɾ��
	 */
	
	//��ѯ����ͼ
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
