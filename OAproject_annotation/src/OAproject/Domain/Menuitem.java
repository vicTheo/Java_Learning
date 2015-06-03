package OAproject.Domain;

import java.io.Serializable;
import java.util.Set;

public class Menuitem implements Serializable {
 private String name;
 private String icon;
 private Boolean isParent;
 private Long mid;
 private Long pid;
 
 private Set<User> users;

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getIcon() {
	return icon;
}

public void setIcon(String icon) {
	this.icon = icon;
}

public Boolean getIsParent() {
	return isParent;
}

public void setIsParent(Boolean isParent) {
	this.isParent = isParent;
}

public Long getMid() {
	return mid;
}

public void setMid(Long mid) {
	this.mid = mid;
}

public Long getPid() {
	return pid;
}

public void setPid(Long pid) {
	this.pid = pid;
}

public Set<User> getUsers() {
	return users;
}

public void setUsers(Set<User> users) {
	this.users = users;
}
 
}
