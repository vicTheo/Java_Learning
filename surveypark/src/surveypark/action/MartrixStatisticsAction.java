package surveypark.action;

import java.text.DecimalFormat;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import surveypark.domain.OptionStatisticsModel;
import surveypark.domain.Question;
import surveypark.domain.QuestionStatisticsModel;
import surveypark.service.StatisticsService;
@Controller("martrixStatisticsAction")
@Scope("prototype")
public class MartrixStatisticsAction extends BaseAction<Question> {

	private static final long serialVersionUID = 6524129193018957219L;
	@Resource(name="statisticsService")
	private StatisticsService statisticsService;
    private Integer qid;
    private QuestionStatisticsModel qsm;
    private String[] colors={
    		"#ff0000",
    		"#00ff00",
    		"#0000ff",
    		"#ffff00",
    		"#ff00ff",
    		"#000fff",
    };
    
	public String[] getColors() {
		return colors;
	}
	public Integer getQid() {
		return qid;
	}
	public void setQid(Integer qid) {
		this.qid = qid;
	}
	public QuestionStatisticsModel getQsm() {
		return qsm;
	}
	public void setQsm(QuestionStatisticsModel qsm) {
		this.qsm = qsm;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    public String execute(){
    	this.qsm=statisticsService.statistics(qid);
    	return ""+qsm.getQuestion().getQuestionType();
    }
    
    public String getScale(int rst,int cst){
    	int count=0;
    	for(OptionStatisticsModel osm:qsm.getOsms()){
    		if(rst==osm.getMatrixRowIndex()&&cst==osm.getMatrixColIndex()){
    			count=osm.getCount();
    			break;
    		}
    	}
    	float scale=0;
    	if(qsm.getCount()!=0){
    	   scale=(float)count/(float)qsm.getCount();
    	}
    	scale=scale*100;
    	DecimalFormat format=new DecimalFormat();
    	format.applyPattern("#,###.00");
		return ""+count+"("+format.format(scale)+"%)";
    }
    
    public String getScale(int rindex,int cindex,int selectindex){
    	int count=0;
    	for(OptionStatisticsModel osm:qsm.getOsms()){
    		if(rindex==osm.getMatrixRowIndex()&&cindex==osm.getMatrixColIndex()&&
    				selectindex==osm.getMatrixSelectIndex()){
    			count=osm.getCount();
    			break;
    		}
    	}
    	float scale=0;
    	if(qsm.getCount()!=0){
    		scale=(float)count/(float)qsm.getCount();
    	}
    	scale=scale*100;
    	DecimalFormat format=new DecimalFormat();
    	format.applyPattern("#,###.00");
    	return ""+count+"("+format.format(scale)+"%)";
    }
   
    public int getPercent(int rindex,int cindex,int selectindex){
    	int count=0;
    	for(OptionStatisticsModel osm:qsm.getOsms()){
    		if(rindex==osm.getMatrixRowIndex()&&cindex==osm.getMatrixColIndex()&&
    				selectindex==osm.getMatrixSelectIndex()){
    			count=osm.getCount();
    			break;
    		}
    	}
    	float scale=0;
    	if(qsm.getCount()!=0){
    		scale=(float)count/(float)qsm.getCount();
    	}
    	scale=scale*100;
    
    	return (int) scale;
    }
}
