package surveypark.domain.log;

import java.util.Date;

/*
 * 日志实体
 */
public class Log{
private Integer id ;
	
	//操作人 
	private String operator ;
	
	//操作时间
	private Date operTime = new Date();
	
	//操作名称(方法名)
	private String operName ;
	
	//操作参数
	private String operParams ;
	
	//结果:success|failure
	private String operResult;
	
	//结果消息
	private String resultMsg ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getOperParams() {
		return operParams;
	}

	public void setOperParams(String operParams) {
		this.operParams = operParams;
	}

	public String getOperResult() {
		return operResult;
	}

	public void setOperResult(String operResult) {
		this.operResult = operResult;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	
	
}
