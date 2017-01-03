package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.Service;

public class DrinkListInfo extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		String cardId = req.getParameter("cardId");
		String type=req.getParameter("type");
		System.out.println("cardId is:"+cardId);
		Service serv =new Service();
		String drinkListInfo=serv.getDrinkListInfo(cardId,type);
		 response.setCharacterEncoding("UTF-8");
	     response.setContentType("text/html");
	     PrintWriter out = response.getWriter();
	     System.out.println(drinkListInfo);
	     out.print(drinkListInfo);
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
