package cn.spring.jdbc;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class PersonDaoImpl2 implements PersonDao{
    private JdbcTemplate jdbcTemplate;
    
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void savePerson() {
      this.jdbcTemplate.execute("insert into course (cid, cname, description) values (11,'11','11')");		
	}

	public List<Person> getPerson() {
List<Person> list=this.jdbcTemplate.query("select * from course",new RowMapperImpl());
			return list;
	}

}
