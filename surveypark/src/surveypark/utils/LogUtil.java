package surveypark.utils;

import java.util.Calendar;

public class LogUtil {
	
    //生成日志表名
   public static String generateLogTableName(int offset){
	   Calendar c=Calendar.getInstance();
	   int year=c.get(Calendar.YEAR);
	   int month=c.get(Calendar.MONTH)+1+offset;
	   if(month>12){
		   year=year+1;
		   month=month-12;
	   }else if(month<0){
		   year=year-1;
		   month=month+12;
	   }
	   String table_name="log_"+"_"+year+"_"+month;
	   return table_name;
   }
}
