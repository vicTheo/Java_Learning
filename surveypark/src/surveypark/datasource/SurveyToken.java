package surveypark.datasource;

import surveypark.domain.Survey;

/**
 * 调查令牌,绑定到当前的线程,传播到数据源路由器.进行分库判断
 */
public class SurveyToken {
	private Survey currentSurvey;
	private static ThreadLocal<SurveyToken> t=new ThreadLocal<SurveyToken>();
	public Survey getCurrentSurvey() {
		return currentSurvey;
	}
	public void setCurrentSurvey(Survey currentSurvey) {
		this.currentSurvey = currentSurvey;
	}

	
	/*
	 * 将令牌对象绑定到当前线程
	 */
	public static void bindCurrentToken(SurveyToken token){
		t.set(token);
	}
	/*
	 * 获取当前线程绑定的token
	 */
   public static SurveyToken getCurrentToken(){
	   return t.get();
   }
   /*
    * 接触令牌的绑定
    */
   public static void unbindCurrentToken(){
	  t.remove();
   }
}
