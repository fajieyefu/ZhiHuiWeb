package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.Service;

public class SaveDataServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String account=req.getParameter("account");
		account=new String(account.getBytes("ISO-8859-1"), "UTF-8");
		String nickname=req.getParameter("nickname");
		nickname=new String(nickname.getBytes("ISO-8859-1"), "UTF-8");
		String sex=req.getParameter("sex");
		sex=new String(sex.getBytes("ISO-8859-1"), "UTF-8");
		String birthday=req.getParameter("birthday");
		birthday=new String(birthday.getBytes("ISO-8859-1"), "UTF-8");
		String address= req.getParameter("address");
		address=new String(address.getBytes("ISO-8859-1"), "UTF-8");
		String introduce=req.getParameter("introduce");
		introduce=new String(introduce.getBytes("ISO-8859-1"), "UTF-8");
		Service serv = new Service();
		String  info = serv.saveData(account, nickname, sex,birthday,address,introduce);
		resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        System.out.println(info);
        out.print(info);
        out.flush();
        out.close();

		
	}

}
