package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.Service;

public class BindCupServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cupId = req.getParameter("cupId");
		String account=req.getParameter("account");
		String studentName=req.getParameter("studentName");
		studentName = new String(studentName.getBytes("ISO-8859-1"), "UTF-8");
		
		System.out.println("cupId is:"+cupId+"  account is:"+account);
		Service serv =new Service();
		String bindInfo=serv.getBindInfo(cupId, account,studentName);
		 resp.setCharacterEncoding("UTF-8");
	     resp.setContentType("text/html");
	     PrintWriter out = resp.getWriter();
	     out.print(bindInfo);
	     out.flush();
	     out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	

}
