package OAproject.Domain;

import java.util.Date;
import java.util.Set;

public class Form {
 private Long fid;
 private String title;
 private String applicator;
 private Date applicateTime;
 private String state;
 private Set<Approval> approvals; 
 private FormTemplate formTemplate;
public Long getFid() {
	return fid;
}
public void setFid(Long fid) {
	this.fid = fid;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getApplicator() {
	return applicator;
}
public void setApplicator(String applicator) {
	this.applicator = applicator;
}
public Date getApplicateTime() {
	return applicateTime;
}
public void setApplicateTime(Date applicateTime) {
	this.applicateTime = applicateTime;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public Set<Approval> getApprovals() {
	return approvals;
}
public void setApprovals(Set<Approval> approvals) {
	this.approvals = approvals;
}
public FormTemplate getFormTemplate() {
	return formTemplate;
}
public void setFormTemplate(FormTemplate formTemplate) {
	this.formTemplate = formTemplate;
}
 
 
}
