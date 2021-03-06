package surveypark.domain;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Survey
 */
public class Survey extends BaseEntity{
	private static final long serialVersionUID = 2386996558507409430L;
	private Integer id;
	private String title = "未命名";
	private String preText = "上一步";
	private String nextText = "下一步";
	private String exitText = "退出";
	private String doneText = "完成";
	private Date createTime = new Date();
	private boolean closed;
	private String logoPhotpPath;
	//最大排序页
	private float maxOrderNum;
	//最小排序页
	private float minOrderNum;
	   
	public float getMaxOrderNum() {
		return maxOrderNum;
	}

	public void setMaxOrderNum(float maxOrderNum) {
		this.maxOrderNum = maxOrderNum;
	}

	public float getMinOrderNum() {
		return minOrderNum;
	}

	public void setMinOrderNum(float minOrderNum) {
		this.minOrderNum = minOrderNum;
	}

	public String getLogoPhotpPath() {
		return logoPhotpPath;
	}

	public void setLogoPhotpPath(String logoPhotpPath) {
		this.logoPhotpPath = logoPhotpPath;
	}

	public boolean getClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	//建立从Survey到User之间多对一关联关系
	private User user ;
	
	//建立从Survey到Page之间一对多关联关系
	private Set<Page> pages = new HashSet<Page>();

	public Set<Page> getPages() {
		return pages;
	}

	public void setPages(Set<Page> pages) {
		this.pages = pages;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPreText() {
		return preText;
	}

	public void setPreText(String preText) {
		this.preText = preText;
	}

	public String getNextText() {
		return nextText;
	}

	public void setNextText(String nextText) {
		this.nextText = nextText;
	}

	public String getExitText() {
		return exitText;
	}

	public void setExitText(String exitText) {
		this.exitText = exitText;
	}

	public String getDoneText() {
		return doneText;
	}

	public void setDoneText(String doneText) {
		this.doneText = doneText;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
