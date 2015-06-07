package OAproject.Domain;

import java.util.Date;

public class Approval {
 private Long aid;
 private String approvename;//…Û≈˙»À
 private String comment;
 private String isapproved;
 private Form form;
 private Date approvetime;
public Long getAid() {
	return aid;
}
public void setAid(Long aid) {
	this.aid = aid;
}
public String getApprovename() {
	return approvename;
}
public void setApprovename(String approvename) {
	this.approvename = approvename;
}
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
public String getIsapproved() {
	return isapproved;
}
public void setIsapproved(String isapproved) {
	this.isapproved = isapproved;
}
public Form getForm() {
	return form;
}
public void setForm(Form form) {
	this.form = form;
}
public Date getApprovetime() {
	return approvetime;
}
public void setApprovetime(Date approvetime) {
	this.approvetime = approvetime;
}
 
}
