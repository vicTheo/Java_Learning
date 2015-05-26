class  ThreadA extends Thread
{
	private static Object lockA=null;
	private static Object lockB=null;
	public ThreadA(Object a,Object b){
	     this.lockA=a;
		 this.lockB=b;
	}
	public void run(){
	        synchronized(lockA){
			   System.out.println("threadA----lockA");
			   try{
			   sleep(3000);
			   }catch(InterruptedException e){
			   e.printStackTrace();
			   }
			   synchronized(lockB){
			   System.out.println("threadA----lockB");
			   }
			}
			System.out.println("threadA----finished");
	}
}
