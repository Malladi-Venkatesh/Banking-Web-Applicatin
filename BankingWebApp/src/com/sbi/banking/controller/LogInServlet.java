package com.sbi.banking.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.sbi.banking.model.LogInBean;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet(name = "loginservlet", urlPatterns = { "/loginservlet" })
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idn=request.getParameter("id");
		long id=Long.parseLong(idn);
		String pwd=request.getParameter("pwd");
		LogInBean loginbean=new LogInBean();
		loginbean.setId(id);
		loginbean.setPassword(pwd);
		boolean validate=false;
		try {
			validate=loginbean.validate(request);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(validate) {
			RequestDispatcher rd=request.getRequestDispatcher("homePage.jsp");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd=request.getRequestDispatcher("logInFailure.jsp");
			rd.forward(request, response);
		}
	}

}
