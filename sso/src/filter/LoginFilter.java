package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
         HttpServletRequest request=(HttpServletRequest) arg0;
         HttpServletResponse response=(HttpServletResponse) arg1;
         if(request.getSession().getAttribute("user")==null){
        	 Cookie[] cookies=request.getCookies();
        	 if(cookies!=null&&cookies.length>0){
        		 for(Cookie c:cookies){
        			 if("sso".equals(c.getName())){
        				 request.getSession().setAttribute("user",c.getValue());
        			 }
        		 }
        		
        	 }
        	 
         }
         chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
