package cn.spring.jdbc;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class PersonDaoImpl  extends JdbcDaoSupport implements PersonDao {

	public void savePerson() {
       		this.getJdbcTemplate().execute("insert into course (cid, cname, description) values (11,'11','11')");
	}

	public List<Person> getPerson() {
		List<Person> list=this.getJdbcTemplate().query("select * from course",new RowMapperImpl());
			return list;
	}

}
