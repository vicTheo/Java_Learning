package action;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ChartAction extends ActionSupport{
	//必须声明此变量(或者把chart放入ActionContext的map集合中),以便于result中通过ActionInvocation.getStack().findValue("chart");
	private JFreeChart chart;
	
	public JFreeChart getChart() {
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

	public String execute(){
		//1、生成一个最基本的饼图(Model)、DefaultPieDataset
		DefaultPieDataset pie=new DefaultPieDataset();
		//给饼图设置数据
		pie.setValue("java", 300);
		pie.setValue("php", 100);
		pie.setValue("c", 200);
		//3、生成一个完整有数据的饼图，通过ChartFactory方法->createPieChart(生成普通形状)
				/*
				 *  String title : 饼图标题
					PieDataset dataset : 数据集
					boolean legend ：是否显示图例
					boolean tooltips : 是否生成工具
					boolean urls : 是否生成URL链接
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
