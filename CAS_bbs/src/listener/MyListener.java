package listener;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;

import domain.User;

public class MyListener implements HttpSessionAttributeListener {

	public void attributeAdded(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		String key=se.getName();
		if(key.equals(AbstractCasFilter.CONST_CAS_ASSERTION)){
			Assertion as=(Assertion) se.getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
			Map<String,Object> map=as.getPrincipal().getAttributes();
			User user=new User();
			user.setId((String)map.get("id"));
			user.setUsername((String)map.get("username"));
			user.setPassword((String)map.get("password"));
			try {
				user.setAddress(URLDecoder.decode((String)map.get("address"),"utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			se.getSession().setAttribute("user", user);
		}
	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}

	

}
