package com.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.Service;


public class UserInfoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String account = req.getParameter("account");
		Service serv = new Service();
		String info = serv.getUserInfo(account);
		System.out.println(info);
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print(info);
        out.flush();
        out.close();
	}

}
