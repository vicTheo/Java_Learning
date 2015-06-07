package db;

import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class CreateDB {
    @Test
    public void createDB(){
    	Configuration configuration=new Configuration();
    	configuration.configure("jbpm.hibernate.cfg.xml");
    	configuration.buildSessionFactory();
    }
}
