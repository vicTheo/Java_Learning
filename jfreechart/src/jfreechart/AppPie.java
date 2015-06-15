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
	 * ��ͼ
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//���ñ�ͼ���ݼ�
		DefaultPieDataset dataset=new DefaultPieDataset();
		dataset.setValue("Jboss",2000);
		dataset.setValue("Oracle",7000);
		dataset.setValue("IBM",3600);
		dataset.setValue("����",2700);
		//��ͼ����
		String title="����˾JEE AS�г�ռ����";
	JFreeChart	chart=ChartFactory.createPieChart3D(title, dataset, true, false, false);
        //����
	    Font font=new Font("����",Font.BOLD,25);
	    chart.getTitle().setFont(font);//����
	    chart.getLegend().setItemFont(new Font("����",Font.PLAIN,18));
	    //��ͼ��
	    PiePlot plot=(PiePlot) chart.getPlot();
	    plot.setLabelFont(new Font("����",Font.PLAIN,15));
	    //����
	    chart.setBackgroundImage(ImageIO.read(new File("e:\\1.jpg")));//ͼ��������
	    plot.setBackgroundImage(ImageIO.read(new File("e:\\2.jpg"))); //��ͼ������
	    //���÷���Ч��
	    plot.setExplodePercent("IBM", 0.1f);
	    plot.setExplodePercent("Jboss", 0.5f);
	    //����ǰ��ɫ͸��
	    plot.setForegroundAlpha(0.8f);
	    //���ñ�ǩ������
	    /*
	     * {0}��˾��
	     * {1}����
	     * {2}�ٷֱ�
	     * {3}����
	     * 
	     */
	    plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}({1}/{3}-{2})"));
	    ChartUtilities.saveChartAsJPEG(new File("e:\\pie.jpg"), chart, 800, 500);
	}

}
