package OAproject.Domain;

import java.io.Serializable;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

public class Kynamic implements Serializable{
  private Long pid;
  private Long kid;
  private String name;
  private Boolean isParent;
  private Set<Version> versions;
public Long getPid() {
	return pid;
}
public void setPid(Long pid) {
	this.pid = pid;
}
public Long getKid() {
	return kid;
}
public void setKid(Long kid) {
	this.kid = kid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Boolean getIsParent() {
	return isParent;
}
public void setIsParent(Boolean isParent) {
	this.isParent = isParent;
}
@JSON(serialize=false)
public Set<Version> getVersions() {
	return versions;
}
public void setVersions(Set<Version> versions) {
	this.versions = versions;
}
  
}
