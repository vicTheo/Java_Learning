package action;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ChartAction extends ActionSupport{
	//���������˱���(���߰�chart����ActionContext��map������),�Ա���result��ͨ��ActionInvocation.getStack().findValue("chart");
	private JFreeChart chart;
	
	public JFreeChart getChart() {
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

	public String execute(){
		//1������һ��������ı�ͼ(Model)��DefaultPieDataset
		DefaultPieDataset pie=new DefaultPieDataset();
		//����ͼ��������
		pie.setValue("java", 300);
		pie.setValue("php", 100);
		pie.setValue("c", 200);
		//3������һ�����������ݵı�ͼ��ͨ��ChartFactory����->createPieChart(������ͨ��״)
				/*
				 *  String title : ��ͼ����
					PieDataset dataset : ���ݼ�
					boolean legend ���Ƿ���ʾͼ��
					boolean tooltips : �Ƿ����ɹ���
					boolean urls : �Ƿ�����URL����
				 * */
		//JFreeChart chart = ChartFactory.createPieChart("IT Language", pie, true, true, false);
		this.chart = ChartFactory.createPieChart("IT Language", pie, true, true, false);
	   
		//ActionContext.getContext().put("chart", chart);
		return SUCCESS;
  }
  
  public String oa(){
	  
	  return "success";
  }
}
