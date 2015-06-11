package CredentialsToPrincipalResolver;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.jasig.cas.authentication.principal.Credentials;
import org.jasig.cas.authentication.principal.CredentialsToPrincipalResolver;
import org.jasig.cas.authentication.principal.Principal;
import org.jasig.cas.authentication.principal.SimplePrincipal;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;

public class MyReturnMoreInfoCredentialsToPrincipalResolver implements
		CredentialsToPrincipalResolver {
    private DataSource dataSource;
    
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Principal resolvePrincipal(Credentials credentials) {
		 final UsernamePasswordCredentials usernamePasswordCredentials = (UsernamePasswordCredentials) credentials;
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			String id="";
			Map<String,Object> map=new HashMap<String,Object>();
			String username=usernamePasswordCredentials.getUsername();
			String password=usernamePasswordCredentials.getPassword();
			map.put("username", username);
			map.put("password", password);
			try {
				//处理中文
				map.put("address",URLEncoder.encode("北京","UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
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
						map.put("id", id);
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
			
			return new SimplePrincipal(id, map);
	}

	public boolean supports(Credentials credentials) {
		return credentials != null
	            && UsernamePasswordCredentials.class.isAssignableFrom(credentials
	                .getClass());
	}

}
