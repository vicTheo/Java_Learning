package cn.geekbook.admin.admin.web.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.geekbook.admin.admin.domain.Admin;
import cn.geekbook.admin.admin.service.AdminService;
import cn.geekbook.commons.CommonUtils;
import cn.geekbook.servlet.BaseServlet;






public class AdminServlet extends BaseServlet {
	private AdminService adminService = new AdminService();
	
	/**
	 * 登录功能
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 封装表单数据到Admin
		 */
		Admin form = CommonUtils.toBean(req.getParameterMap(), Admin.class);
		Admin admin = adminService.login(form);
		if(admin == null) {
			req.setAttribute("msg", "用户名或密码错误");
			return "/adminjsps/login/login.jsp";
		}
		req.getSession().setAttribute("admin", admin);
		
		return "/adminjsps/index.jsp";
	}
	public String quit(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getSession().invalidate();
		return "r:/adminjsps/index.jsp";
	}
}
