package cn.spring.jdbc.transaction;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class PersonDaoImpl extends JdbcDaoSupport implements PersonDao{

	public void savePerson() {
		this.getJdbcTemplate().execute("insert into person (pid,pname) values (4,'xiaoli')");
		int i=1/0;
		this.getJdbcTemplate().execute("insert into person (pid,pname) values (5,'xiaoli')");
		
	}

}
