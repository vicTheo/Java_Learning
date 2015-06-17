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
		//创建xls文件对象
		HSSFWorkbook wb=new HSSFWorkbook();
		//创建sheet对象
		HSSFSheet sheet= wb.createSheet("first sheet");
		//创建一行
		HSSFRow row=sheet.createRow(0);
		//创建单元格
		HSSFCell cell0=row.createCell(0);
		HSSFCell cell1=row.createCell(1);
		HSSFCell cell2=row.createCell(2);
		HSSFCell cell3=row.createCell(3);
		HSSFCell cell4=row.createCell(4);
		//插入值
		cell0.setCellValue(false);
		cell1.setCellValue(Calendar.getInstance());
		cell2.setCellValue(new Date());
		cell3.setCellValue(33333.555);
		cell4.setCellValue("you would never");
		//格式化数据
		HSSFCellStyle style=wb.createCellStyle();//创建样式对象
		HSSFDataFormat format=wb.createDataFormat();//创建格式对象
		
		style.setDataFormat(format.getFormat("yyyy-MM-dd hh-mm-ss"));//设置格式
		cell1.setCellStyle(style);//对cell应用格式
		row.getCell(2).setCellStyle(style);
		
		//设置列宽
		sheet.setColumnWidth(0, 8000);
		sheet.autoSizeColumn(2);//自动列宽
		
		//数字格式化
		style=wb.createCellStyle();
		style.setDataFormat(format.getFormat("##,###.0000"));
		row.getCell(3).setCellStyle(style);
		
		//文本自动换行
		style=wb.createCellStyle();
		style.setWrapText(true);
		sheet.setColumnWidth(4, 2000);
		row.getCell(4).setCellStyle(style);
		
		//设置文本对齐方式
		sheet.setColumnWidth(0, 5000);
		HSSFRow row1=sheet.createRow(1);
		row1.createCell(0).setCellValue("左上");
		row1.createCell(1).setCellValue("中中");
		row1.createCell(2).setCellValue("右下");
		
		//对齐 左上
		style=wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		row1.getCell(0).setCellStyle(style);
		//对齐 中中
		style=wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		row1.getCell(1).setCellStyle(style);
		//对齐 右下
		style=wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);
		row1.getCell(2).setCellStyle(style);
		row1.setHeightInPoints(50);//设置行高
		
		//设置字体
		style=row1.getCell(1).getCellStyle();
		HSSFFont font=wb.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 20);
		font.setColor(HSSFColor.RED.index);
		style.setFont(font);
		//文本旋转
		style.setRotation((short) -30);
		
		//设置边框
		HSSFRow row2=sheet.createRow(2);
		style=wb.createCellStyle();
		style.setBorderTop(HSSFCellStyle.BORDER_DASH_DOT_DOT);
		style.setTopBorderColor(HSSFColor.RED.index);
		row2.createCell(0).setCellStyle(style);
		
		//计算列
		row2=sheet.createRow(3);
		row2.createCell(0).setCellValue(20);
		row2.createCell(1).setCellValue(20.5);
		row2.createCell(2).setCellValue(30);
		row2.createCell(3).setCellFormula("sum(A4:C4)");
		
		//整体移动行
		sheet.shiftRows(2, 4, 2);

		//拆分窗格
		//1000:左侧窗格的宽度
		//2000:上侧窗格的高度
		//3:右侧窗格开始显示的列的索引
		//4:下侧窗格开始显示的行的索引
		//1:激活的哪个面板区
		//sheet.createSplitPane(1000, 2000, 3, 4,1);
		
		//冻结
		sheet.createFreezePane(1, 2, 3, 4);
		//写入到磁盘文件/
		wb.write(new FileOutputStream(new File("e:\\poi.xls")));
	}
}
