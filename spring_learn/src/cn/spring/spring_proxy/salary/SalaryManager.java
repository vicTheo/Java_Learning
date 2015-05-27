package cn.spring.spring_proxy.salary;

public class SalaryManager {
   private Logger logger;
   private Security security;
   private Privilege privilege;
   public SalaryManager(Logger logger,Security security,Privilege privilege){
	   this.logger=logger;
	   this.privilege=privilege;
	   this.security=security;
   }
   public void showSalary(){
	   this.logger.logging();
	   this.security.secure();
	   if("admin".equals(this.privilege.getAccess())){
		   System.out.println("工资涨了2万日元");
	   }else{
		   System.out.println("你还没有权限");
	   }
   }
}
