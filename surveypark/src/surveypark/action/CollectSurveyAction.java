package surveypark.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import surveypark.domain.Answer;
import surveypark.domain.Question;
import surveypark.service.SurveyService;
@Controller("collectSurveyAction")
@Scope("prototype")
public class CollectSurveyAction extends BaseAction<Question> {

	private static final long serialVersionUID = -3572414502537878173L;
    @Resource(name="surveyService")
	private SurveyService surveyService;
    private Integer sid;
 
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	

    
	public String execute(){
     return "success";
	}
	public InputStream getInputStream() {
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("suveypark sheet");
		List<Question> questions=surveyService.getAllQuestions(sid);
		HSSFCellStyle style=wb.createCellStyle();
		style.setWrapText(true);  //自动换行
		HSSFRow row=sheet.createRow(0);
		Map<Integer,Integer> question2index =new HashMap<Integer, Integer>();
		HSSFCell cell=null;
		//创建表头
		for(int i=0;i<questions.size();i++){
			cell=row.createCell(i);
			cell.setCellValue(questions.get(i).getTitle());
			cell.setCellStyle(style);
			sheet.setColumnWidth(i, 8000);
			question2index.put(questions.get(i).getId(),i);
		}
		//插入内容
		List<Answer> answers=surveyService.getAllAnswers(sid);
		String olduuid="";
		String newuuid="";
		int index=0;
		for(Answer a:answers){
			olduuid=a.getUuid();
			if(!olduuid.equals(newuuid)){
				index++;
				newuuid=olduuid;
				row=sheet.createRow(index);
			}
			cell=row.createCell(question2index.get(a.getQuestionId()));
			cell.setCellValue(a.getAnswerIds());
			cell.setCellStyle(style);
		}
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		ByteArrayInputStream bais=null;
		try {
			wb.write(baos);
			bais=new ByteArrayInputStream(baos.toByteArray());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return bais;
	}
}
