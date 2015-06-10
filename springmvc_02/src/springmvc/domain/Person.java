package springmvc.domain;

public class Person {
private String name;
private Long id;
private String address;

public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
@Override
public String toString() {
	// TODO Auto-generated method stub
	return "{id="+id+",name="+name+"}";
}

}
