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
	 * ����ͼ
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		DefaultCategoryDataset dataset=new DefaultCategoryDataset();
		dataset.addValue(2000, "IBM", "һ����");
		dataset.addValue(2300, "ORACLE", "һ����");
		dataset.addValue(2800, "JBOSS", "һ����");
		dataset.addValue(3300, "����", "һ����");
		
		dataset.addValue(4800, "IBM", "������");
		dataset.addValue(4300, "ORACLE", "������");
		dataset.addValue(3200, "JBOSS", "������");
		dataset.addValue(1800, "����", "������");
		
		dataset.addValue(1500, "IBM", "������");
		dataset.addValue(2600, "ORACLE", "������");
		dataset.addValue(3900, "JBOSS", "������");
		dataset.addValue(2100, "����", "������");
		
		JFreeChart chart=ChartFactory.createLineChart3D("����˾JEE AS�г�ռ����", "����", "����", dataset, PlotOrientation.VERTICAL, true, false, false);
		  //����
		chart.getTitle().setFont(new Font("����",Font.BOLD,25));
		chart.getLegend().setItemFont(new Font("����",Font.PLAIN,18));
		//��ͼ��
		CategoryPlot plot=chart.getCategoryPlot();
		//��������
		plot.getDomainAxis().setLabelFont(new Font("����",Font.PLAIN,15));
		plot.getDomainAxis().setTickLabelFont(new Font("����",Font.PLAIN,15));//С��ǩ
		//range
		plot.getRangeAxis().setLabelFont(new Font("����",Font.PLAIN,15));
		//����ǰ��͸��
		plot.setForegroundAlpha(0.8f);
		ChartUtilities.saveChartAsJPEG(new File("e:\\line.jpg"), chart, 800, 500);
	}

}
