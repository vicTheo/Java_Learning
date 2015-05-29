package springtest;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class BaseTest {
  public static ApplicationContext context;
  
  @Before
  public void startSpring(){
	  context=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
  }
}
