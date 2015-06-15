package jfreechart;

import java.awt.Font;
import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class AppLine {

	/**
	 * 折线图
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		DefaultCategoryDataset dataset=new DefaultCategoryDataset();
		dataset.addValue(2000, "IBM", "一季度");
		dataset.addValue(2300, "ORACLE", "一季度");
		dataset.addValue(2800, "JBOSS", "一季度");
		dataset.addValue(3300, "用友", "一季度");
		
		dataset.addValue(4800, "IBM", "二季度");
		dataset.addValue(4300, "ORACLE", "二季度");
		dataset.addValue(3200, "JBOSS", "二季度");
		dataset.addValue(1800, "用友", "二季度");
		
		dataset.addValue(1500, "IBM", "三季度");
		dataset.addValue(2600, "ORACLE", "三季度");
		dataset.addValue(3900, "JBOSS", "三季度");
		dataset.addValue(2100, "用友", "三季度");
		
		JFreeChart chart=ChartFactory.createLineChart3D("各公司JEE AS市场占有量", "季度", "产量", dataset, PlotOrientation.VERTICAL, true, false, false);
		  //中文
		chart.getTitle().setFont(new Font("宋体",Font.BOLD,25));
		chart.getLegend().setItemFont(new Font("宋体",Font.PLAIN,18));
		//绘图区
		CategoryPlot plot=chart.getCategoryPlot();
		//域轴字体
		plot.getDomainAxis().setLabelFont(new Font("宋体",Font.PLAIN,15));
		plot.getDomainAxis().setTickLabelFont(new Font("宋体",Font.PLAIN,15));//小标签
		//range
		plot.getRangeAxis().setLabelFont(new Font("宋体",Font.PLAIN,15));
		//设置前景透明
		plot.setForegroundAlpha(0.8f);
		ChartUtilities.saveChartAsJPEG(new File("e:\\line.jpg"), chart, 800, 500);
	}

}
