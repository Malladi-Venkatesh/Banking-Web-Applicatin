package com.sbi.banking.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbi.banking.dao.LogInDao;
import com.sbi.banking.model.AccountBean;

/**
 * Servlet implementation class DepositServlet
 */
@WebServlet(name = "depositServlet", urlPatterns = { "/depositServlet" })
public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepositServlet() {
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
		String da=request.getParameter("deposit");
		double deposit=Double.parseDouble(da);
		LogInDao logindao=new LogInDao();
		AccountBean accountbean=new AccountBean();
		try {
			Connection con = logindao.getConnection();
			PreparedStatement pstm1 = con.prepareStatement("select *from sbiaccount where acnum=?");
			pstm1.setInt(1, acnum);
			ResultSet rs1=pstm1.executeQuery();
			if(rs1.next()) {
				
				if(deposit<0) {
					con.close();
					response.getWriter().println("<h2 style='color:red;'>You have deposited Invalid Amount...</h2>");
					request.getRequestDispatcher("deposit.jsp").include(request, response);
				}
				
				else {
					PreparedStatement pstm2 = con.prepareStatement("update sbiaccount set balance=balance+? where acnum=?");
					pstm2.setDouble(1,deposit);
					pstm2.setInt(2, acnum);
					ResultSet rs2=pstm2.executeQuery();
					con.close();
					response.getWriter().println("<h3 style='color:red;'>Your amount Rs. "+deposit +"0/ is Deposited...-</h3>");
					request.getRequestDispatcher("homePage.jsp").include(request, response);
				}
			}
			else {
				con.close();
				response.getWriter().println("<h2 style='color:red;'>You have no Account...</h2>");
				request.getRequestDispatcher("homePage.jsp").include(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
