package cn.spring.interfaceOriented;

public class DocumentManager {
 private Doucument document;
 public DocumentManager(Doucument document){
	 this.document=document;
 }
 public void read(){
	 this.document.read();
 }
 public void write(){
	 this.document.write();
 }
}
