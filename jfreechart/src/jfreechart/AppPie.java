package jfreechart;

import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.data.general.DefaultPieDataset;


public class AppPie {

	/**
	 * 饼图
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//设置饼图数据集
		DefaultPieDataset dataset=new DefaultPieDataset();
		dataset.setValue("Jboss",2000);
		dataset.setValue("Oracle",7000);
		dataset.setValue("IBM",3600);
		dataset.setValue("用友",2700);
		//饼图标题
		String title="各大公司JEE AS市场占有量";
	JFreeChart	chart=ChartFactory.createPieChart3D(title, dataset, true, false, false);
        //中文
	    Font font=new Font("宋体",Font.BOLD,25);
	    chart.getTitle().setFont(font);//标题
	    chart.getLegend().setItemFont(new Font("宋体",Font.PLAIN,18));
	    //绘图区
	    PiePlot plot=(PiePlot) chart.getPlot();
	    plot.setLabelFont(new Font("宋体",Font.PLAIN,15));
	    //背景
	    chart.setBackgroundImage(ImageIO.read(new File("e:\\1.jpg")));//图表区背景
	    plot.setBackgroundImage(ImageIO.read(new File("e:\\2.jpg"))); //绘图区背景
	    //设置分裂效果
	    plot.setExplodePercent("IBM", 0.1f);
	    plot.setExplodePercent("Jboss", 0.5f);
	    //设置前景色透明
	    plot.setForegroundAlpha(0.8f);
	    //设置标签生成器
	    /*
	     * {0}公司名
	     * {1}销量
	     * {2}百分比
	     * {3}总量
	     * 
	     */
	    plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}({1}/{3}-{2})"));
	    ChartUtilities.saveChartAsJPEG(new File("e:\\pie.jpg"), chart, 800, 500);
	}

}
