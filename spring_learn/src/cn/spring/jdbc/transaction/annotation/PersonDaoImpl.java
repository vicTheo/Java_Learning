package cn.spring.jdbc.transaction.annotation;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("personDao")
public class PersonDaoImpl  implements PersonDao{
	@Resource(name="jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
	
	public void savePerson() {
		this.jdbcTemplate.execute("insert into person (pid,pname) values (4,'xiaoli')");
		int i=1/0;
		this.jdbcTemplate.execute("insert into person (pid,pname) values (5,'xiaoli')");
		
	}

}
