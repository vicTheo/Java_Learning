package cn.spring.spring_proxy.salary_proxy;

public class SalaryManageProxy implements SalaryManager{
   private Logger logger;
   private Security security;
   private Privilege privilege;
   private SalaryManager target;
   public SalaryManageProxy(Logger logger,Security security,Privilege privilege,SalaryManager target){
	   this.logger=logger;
	   this.privilege=privilege;
	   this.security=security;
	   this.target=target;
   }
   public void showSalary(){
	   this.logger.logging();
	   this.security.secure();
	   if("admin".equals(this.privilege.getAccess())){
		   this.target.showSalary();
	   }else{
		   System.out.println("你还没有权限");
	   }
   }
}
