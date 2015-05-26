package cn.geekbook.user.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.geekbook.commons.CommonUtils;
import cn.geekbook.servlet.BaseServlet;
import cn.geekbook.user.domain.User;

public class InitSessionUserServlet extends BaseServlet {

	/*
	 * 初始化sessionUser
	 */
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	    
		if(req.getSession().getAttribute("sessionUser")==null){
			User sessionUser=new User();
			String str1=CommonUtils.uuid();
			String str2=str1.substring(0,4);
			sessionUser.setUid(str1);
			sessionUser.setLoginname(str2);
			// 保存用户到session
			HttpSession session=req.getSession();
			System.out.println(session.getId());
			session.setAttribute("sessionUser", sessionUser);
			
			
		}
	req.getRequestDispatcher("/jsps/main.jsp").forward(req, resp);
	}

}
