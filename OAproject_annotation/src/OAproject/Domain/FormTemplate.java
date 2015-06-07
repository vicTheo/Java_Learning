package OAproject.Domain;

import java.io.Serializable;
import java.util.Set;

public class FormTemplate implements Serializable{
private Long ftid;
private String name;
private String processkey;//jbpm中的pdkey
private String url;       //表单模板的存取路径
private Set<Form> forms;
public Long getFtid() {
	return ftid;
}
public void setFtid(Long ftid) {
	this.ftid = ftid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getProcesskey() {
	return processkey;
}
public void setProcesskey(String processkey) {
	this.processkey = processkey;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public Set<Form> getForms() {
	return forms;
}
public void setForms(Set<Form> forms) {
	this.forms = forms;
}

}
