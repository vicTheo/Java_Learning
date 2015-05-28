package cn.spring.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;



public class PersonDaoImpl3 extends JdbcTemplate implements PersonDao{
    public PersonDaoImpl3(DataSource dataSource){
    	super(dataSource);
    }
	public void savePerson() {
		// TODO Auto-generated method stub
		this.execute("insert into course (cid, cname, description) values (11,'11','11')");
	}
	public List<Person> getPerson() {
        List<Person> list=this.query("select * from course",new RowMapperImpl());
		return list;
	}

}
