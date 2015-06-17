package poi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class Poi {
   
	public static void main(String[] args) throws FileNotFoundException, IOException{
		//����xls�ļ�����
		HSSFWorkbook wb=new HSSFWorkbook();
		//����sheet����
		HSSFSheet sheet= wb.createSheet("first sheet");
		//����һ��
		HSSFRow row=sheet.createRow(0);
		//������Ԫ��
		HSSFCell cell0=row.createCell(0);
		HSSFCell cell1=row.createCell(1);
		HSSFCell cell2=row.createCell(2);
		HSSFCell cell3=row.createCell(3);
		HSSFCell cell4=row.createCell(4);
		//����ֵ
		cell0.setCellValue(false);
		cell1.setCellValue(Calendar.getInstance());
		cell2.setCellValue(new Date());
		cell3.setCellValue(33333.555);
		cell4.setCellValue("you would never");
		//��ʽ������
		HSSFCellStyle style=wb.createCellStyle();//������ʽ����
		HSSFDataFormat format=wb.createDataFormat();//������ʽ����
		
		style.setDataFormat(format.getFormat("yyyy-MM-dd hh-mm-ss"));//���ø�ʽ
		cell1.setCellStyle(style);//��cellӦ�ø�ʽ
		row.getCell(2).setCellStyle(style);
		
		//�����п�
		sheet.setColumnWidth(0, 8000);
		sheet.autoSizeColumn(2);//�Զ��п�
		
		//���ָ�ʽ��
		style=wb.createCellStyle();
		style.setDataFormat(format.getFormat("##,###.0000"));
		row.getCell(3).setCellStyle(style);
		
		//�ı��Զ�����
		style=wb.createCellStyle();
		style.setWrapText(true);
		sheet.setColumnWidth(4, 2000);
		row.getCell(4).setCellStyle(style);
		
		//�����ı����뷽ʽ
		sheet.setColumnWidth(0, 5000);
		HSSFRow row1=sheet.createRow(1);
		row1.createCell(0).setCellValue("����");
		row1.createCell(1).setCellValue("����");
		row1.createCell(2).setCellValue("����");
		
		//���� ����
		style=wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		row1.getCell(0).setCellStyle(style);
		//���� ����
		style=wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		row1.getCell(1).setCellStyle(style);
		//���� ����
		style=wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);
		row1.getCell(2).setCellStyle(style);
		row1.setHeightInPoints(50);//�����и�
		
		//��������
		style=row1.getCell(1).getCellStyle();
		HSSFFont font=wb.createFont();
		font.setFontName("����");
		font.setFontHeightInPoints((short) 20);
		font.setColor(HSSFColor.RED.index);
		style.setFont(font);
		//�ı���ת
		style.setRotation((short) -30);
		
		//���ñ߿�
		HSSFRow row2=sheet.createRow(2);
		style=wb.createCellStyle();
		style.setBorderTop(HSSFCellStyle.BORDER_DASH_DOT_DOT);
		style.setTopBorderColor(HSSFColor.RED.index);
		row2.createCell(0).setCellStyle(style);
		
		//������
		row2=sheet.createRow(3);
		row2.createCell(0).setCellValue(20);
		row2.createCell(1).setCellValue(20.5);
		row2.createCell(2).setCellValue(30);
		row2.createCell(3).setCellFormula("sum(A4:C4)");
		
		//�����ƶ���
		sheet.shiftRows(2, 4, 2);

		//��ִ���
		//1000:��ര��Ŀ��
		//2000:�ϲര��ĸ߶�
		//3:�Ҳര��ʼ��ʾ���е�����
		//4:�²ര��ʼ��ʾ���е�����
		//1:������ĸ������
		//sheet.createSplitPane(1000, 2000, 3, 4,1);
		
		//����
		sheet.createFreezePane(1, 2, 3, 4);
		//д�뵽�����ļ�/
		wb.write(new FileOutputStream(new File("e:\\poi.xls")));
	}
}
