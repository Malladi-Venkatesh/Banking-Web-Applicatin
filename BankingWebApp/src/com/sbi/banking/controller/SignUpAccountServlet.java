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

import com.sbi.banking.dao.LogInDao;

/**
 * Servlet implementation class SignUpAccountServlet
 */
@WebServlet(name = "signUpAccountServlet", urlPatterns = { "/signUpAccountServlet" })
public class SignUpAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpAccountServlet() {
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
		String ids=request.getParameter("id");
		int id=Integer.parseInt(ids);
		String acns=request.getParameter("acnum");
		int acnum=Integer.parseInt(acns);
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String bals=request.getParameter("bal");
		double bal=Double.parseDouble(bals);
		LogInDao logindao=new LogInDao();
		Connection con=null;
		try {
			con = logindao.getConnection();
			con.setAutoCommit(false);
			
			PreparedStatement pstm1 = con.prepareStatement("select *from sbilogin where id=?");
			pstm1.setInt(1, id);
			ResultSet rs1 = pstm1.executeQuery();
			if((rs1.next())){
				PreparedStatement pstm2 = con.prepareStatement("select *from sbiaccount where acnum=?");
				pstm2.setInt(1, acnum);
				ResultSet rs2 = pstm2.executeQuery();
				if(!(rs2.next())) {
					PreparedStatement pstm3 = con.prepareStatement("insert into sbiaccount values (?,?,?,?,?)");
					pstm3.setInt(1, acnum);
					pstm3.setString(2, fname);
					pstm3.setString(3, lname);
					pstm3.setInt(4, id);
					pstm3.setDouble(5, bal);
					pstm3.execute();
					con.commit();
					con.close();
					response.getWriter().println("<h3 style='color:red;'>Bank Account Created Successfully...</h3>");
					request.getRequestDispatcher("logInScreen.jsp").include(request, response);
				}
				else {
					con.close();
					response.getWriter().println("<h3 style='color:red;'>Failed to Create Bank account. Please try with another Account Number..</h3>");
					request.getRequestDispatcher("signUpAccount.jsp").include(request, response);
				}
			}
			else {
				con.close();
				response.getWriter().println("<h3 style='color:red;'>Failed to Create Bank account. Please enter Correct User-Id..</h3>");
				request.getRequestDispatcher("signUpAccount.jsp").include(request, response);
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			try {
				con.rollback();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
