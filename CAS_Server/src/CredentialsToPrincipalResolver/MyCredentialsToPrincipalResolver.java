package CredentialsToPrincipalResolver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.jasig.cas.authentication.principal.AbstractPersonDirectoryCredentialsToPrincipalResolver;
import org.jasig.cas.authentication.principal.Credentials;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;



public class MyCredentialsToPrincipalResolver extends
		AbstractPersonDirectoryCredentialsToPrincipalResolver {
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public boolean supports(Credentials credentials) {
		return credentials != null
	            && UsernamePasswordCredentials.class.isAssignableFrom(credentials
	                .getClass());
	}

	@Override
	protected String extractPrincipalId(Credentials credentials) {
		  final UsernamePasswordCredentials usernamePasswordCredentials = (UsernamePasswordCredentials) credentials;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String id="";
		String username=usernamePasswordCredentials.getUsername();
		String password=usernamePasswordCredentials.getPassword();
		try {
			conn=dataSource.getConnection();
			String sql="select uid from user where username=? and password=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs=ps.executeQuery();
			if(rs!=null){
				if(rs.next()){
					id=rs.getString("uid");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			
			try{
				  if(rs!=null){
					  rs.close();
				  }
				  if(ps!=null){
					  ps.close();
				  }
				  if(conn!=null){
					  conn.close();
				  }
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return id;
	}

}
