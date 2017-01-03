package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.Service;

public class SendMessage extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		String phoneString=req.getParameter("phoneString");
		System.out.println(phoneString);
		Service serv = new Service();
		String kindergartenInfo=serv.getKindegartenInfo(phoneString);
		response.setCharacterEncoding("UTF-8");
	     response.setContentType("text/html");
	     PrintWriter out = response.getWriter();
	     out.print(kindergartenInfo);
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
