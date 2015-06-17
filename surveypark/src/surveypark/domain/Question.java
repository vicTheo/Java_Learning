package surveypark.domain;

import surveypark.utils.StringUtil;


/**
 * 问题实体
 */
public class Question extends BaseEntity{
	
	private static final long serialVersionUID = 3929034623950694227L;
	private final String RN="\r\n";
	private Integer id;
	// 题型0-8
	private int questionType;
	// 标题
	private String title;
	// 选项
	private String options;
	private String[] optionsArr;
	// 其他项
	private boolean other;

	// 其他项样式:0-无 1-文本框 2-下拉列表
	private int otherStyle;
	// 其他项下拉选项
	private String otherSelectOptions;
	private String[] otherSelectOptionsArr;
	// 矩阵式行标题集
	private String matrixRowTitles;
	private String[] matrixRowTitlesArr;
	// 矩阵式列标题集
	private String matrixColTitles;
	private String[] matrixColTitlesArr;
	// 矩阵是下拉选项集
	private String matrixSelectOptions;
	private String[] matrixSelectOptionsArr;
	

	
	public String[] getOptionsArr() {
		return optionsArr;
	}

	public void setOptionsArr(String[] optionsArr) {
		this.optionsArr = optionsArr;
	}

	public String[] getOtherSelectOptionsArr() {
		return otherSelectOptionsArr;
	}

	public void setOtherSelectOptionsArr(String[] otherSelectOptionsArr) {
		this.otherSelectOptionsArr = otherSelectOptionsArr;
	}

	public String[] getMatrixRowTitlesArr() {
		return matrixRowTitlesArr;
	}

	public void setMatrixRowTitlesArr(String[] matrixRowTitlesArr) {
		this.matrixRowTitlesArr = matrixRowTitlesArr;
	}

	public String[] getMatrixColTitlesArr() {
		return matrixColTitlesArr;
	}

	public void setMatrixColTitlesArr(String[] matrixColTitlesArr) {
		this.matrixColTitlesArr = matrixColTitlesArr;
	}

	public String[] getMatrixSelectOptionsArr() {
		return matrixSelectOptionsArr;
	}

	public void setMatrixSelectOptionsArr(String[] matrixSelectOptionsArr) {
		this.matrixSelectOptionsArr = matrixSelectOptionsArr;
	}

	//建立从Question到Page之间多对一关联关系
	private Page page ;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
		this.optionsArr=StringUtil.string2arr(options, RN);
	}

	public boolean isOther() {
		return other;
	}

	public void setOther(boolean other) {
		this.other = other;
	}

	public int getOtherStyle() {
		return otherStyle;
	}

	public void setOtherStyle(int otherStyle) {
		this.otherStyle = otherStyle;
	}

	public String getOtherSelectOptions() {
		return otherSelectOptions;
	}

	public void setOtherSelectOptions(String otherSelectOptions) {
		this.otherSelectOptions = otherSelectOptions;
		this.otherSelectOptionsArr=StringUtil.string2arr(otherSelectOptions, RN);
	}

	public String getMatrixRowTitles() {
		return matrixRowTitles;
	}

	public void setMatrixRowTitles(String matrixRowTitles) {
		this.matrixRowTitles = matrixRowTitles;
		this.matrixRowTitlesArr=StringUtil.string2arr(matrixRowTitles, RN);
	}

	public String getMatrixColTitles() {
		return matrixColTitles;
	}

	public void setMatrixColTitles(String matrixColTitles) {
		this.matrixColTitles = matrixColTitles;
		this.matrixColTitlesArr=StringUtil.string2arr(matrixColTitles, RN);
	}

	public String getMatrixSelectOptions() {
		return matrixSelectOptions;
	}

	public void setMatrixSelectOptions(String matrixSelectOptions) {
		this.matrixSelectOptions = matrixSelectOptions;
		this.matrixSelectOptionsArr=StringUtil.string2arr(matrixSelectOptions, RN);
	}

}
