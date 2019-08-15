package com.sbi.banking.controller;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sbi.banking.dao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbi.banking.dao.LogInDao;
import com.sbi.banking.model.AccountBean;

/**
 * Servlet implementation class CheckBalance
 */
@WebServlet(name = "checkBalance", urlPatterns = { "/checkBalanceServlet" })
public class CheckBalanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckBalanceServlet() {
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
		HttpSession session = request.getSession();
		int acnum=(Integer)session.getAttribute("acnum");
		LogInDao logindao=new LogInDao();
		AccountBean accountbean=new AccountBean();
		try {
			Connection con = logindao.getConnection();
			PreparedStatement pstm = con.prepareStatement("select *from sbiaccount where acnum=?");
			pstm.setInt(1, acnum);
			ResultSet rs=pstm.executeQuery();
			if(rs.next()) {
				accountbean.setAcnum(acnum);
				accountbean.setFname(rs.getString(2));
				accountbean.setLname(rs.getString(3));
				accountbean.setBal(rs.getDouble(5));
				/*
				 * // To display User-Name in homepage i.e remember request is only valid for
				 * one request only //For more than on reqeust it is not applicable we need to
				 * set in session for entire LogIn-Logout Session
				 * request.setAttribute("loginbean", loginbean);
				 */ 
				con.close();
				request.setAttribute("accountbean", accountbean);
				request.getRequestDispatcher("checkBalance.jsp").forward(request, response);
			}
			else {
				con.close();
				response.getWriter().println("<h2 style='color:red;'>You have no Account...</h2>");
				request.getRequestDispatcher("checkBalance.jsp").include(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
