package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.Service;

/*
 * 用于处理客户端的注册请求
 */
public class RegLet extends HttpServlet {

	private static final long serialVersionUID = 369840050351775312L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//
//		// 接收客户端信息
//		String username = request.getParameter("username");
//		username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
//		String password = request.getParameter("password");
//		System.out.println(username + "--" + password);
//
//		// 新建服务对象
//		Service serv = new Service();
//
//		// 验证处理
//		boolean loged = serv.register(username, password);
//		if (loged) {
//			System.out.print("Succss");
//			request.getSession().setAttribute("username", username);
//			// response.sendRedirect("welcome.jsp");
//		} else {
//			System.out.print("Failed");
//		}
//
//		// 返回信息到客户端
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		out.print("用户名：" + username);
//		out.print("密码：" + password);
//		out.flush();
//		out.close();

	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		String phone=req.getParameter("phone");
		phone = new String(phone.getBytes("ISO-8859-1"), "UTF-8");
		String useName=req.getParameter("useName");
		useName = new String(useName.getBytes("ISO-8859-1"), "UTF-8");
		String passWord=req.getParameter("password");
		passWord = new String(passWord.getBytes("ISO-8859-1"), "UTF-8");
		Service serv = new Service();
		String  info = serv.register(useName, passWord, phone);
		resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print(info);
        out.flush();
        out.close();
		
    }
	
}
