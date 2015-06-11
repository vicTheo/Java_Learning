package AuthenticationHandler;

import org.jasig.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;

public class MyAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler{
	 /*
	  * 自定义验证规则
	  * @see org.jasig.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler#authenticateUsernamePasswordInternal(org.jasig.cas.authentication.principal.UsernamePasswordCredentials)
	  */
	public boolean authenticateUsernamePasswordInternal(final UsernamePasswordCredentials credentials) {
	        final String username = credentials.getUsername();
	        final String password = credentials.getPassword();
	        StringBuffer stringBuffer=new StringBuffer(username);
	        if(stringBuffer.reverse().toString().equals(password)){
	        	return true;
	        }
	        
	     return false;
	 }
}
