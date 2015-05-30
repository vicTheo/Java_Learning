/**
 * @author theo
 */
function Person(id){
	this.id=id;
	return id;
}
alert(window.Person(5));
function Student(){}
Student.a=Person;
alert(Student.a(7));
alert(Person.apply(Array,[6]));
