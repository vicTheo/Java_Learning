package cn.spring.spring_proxy.dao_jdkproxy;

public class Transaction {
 public void beginTransaction(){
	 System.out.println("beginTransaction");
 }
 public void commit(){
	 System.out.println("commit");
 }
}
