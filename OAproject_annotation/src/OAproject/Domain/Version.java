package OAproject.Domain;

import java.io.Serializable;
import java.util.Date;

public class Version implements Serializable{
private String title;
private String content;
private Long vid;
private Long version;
private Kynamic kynamic;
private Date updateTime;

public Date getUpdateTime() {
	return updateTime;
}
public void setUpdateTime(Date updateTime) {
	this.updateTime = updateTime;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public Long getVid() {
	return vid;
}
public void setVid(Long vid) {
	this.vid = vid;
}
public Long getVersion() {
	return version;
}
public void setVersion(Long version) {
	this.version = version;
}
public Kynamic getKynamic() {
	return kynamic;
}
public void setKynamic(Kynamic kynamic) {
	this.kynamic = kynamic;
}
 
}
