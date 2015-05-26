class  ThreadB extends Thread
{
    private static Object lockA=null;
	private static Object lockB=null;
	public ThreadB(Object a,Object b){
	     this.lockA=a;
		 this.lockB=b;
	}
	public void run(){
	        synchronized(lockB){
			   System.out.println("threadB----lockB");
			   try{
			   sleep(3000);
			   }catch(InterruptedException e){
			   e.printStackTrace();
			   }
			   synchronized(lockA){
			   System.out.println("threadB----lockA");
			   }
			}
			System.out.println("threadB----finished");
	}
}
