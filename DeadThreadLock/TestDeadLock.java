
public class TestDeadLock {
   
	public static void main(String[] args)throws Exception{
		 Object a=new Object();
	     Object b=new Object();
	  Thread threada=new ThreadA(a,b);
	   Thread threadb=new ThreadB(a,b);
	   threada.start();
	  
	   threadb.start();
	}
}


