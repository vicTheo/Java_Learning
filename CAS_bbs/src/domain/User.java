package domain;

public class User {
  private String id;
  private String username;
  private String password;
  private String address;
  
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
@Override
public String toString() {
	// TODO Auto-generated method stub
	return "{id="+id+",username="+username+",password="+password+",address="+address+"}";
}
  
}
