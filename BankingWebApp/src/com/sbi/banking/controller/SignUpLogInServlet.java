package com.sbi.banking.controller;

import com.sbi.banking.dao.*;
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
 * Servlet implementation class SignUpLogInServlet
 */
@WebServlet(name = "signUpLogInServlet", urlPatterns = { "/signUpLogInServlet" })
public class SignUpLogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpLogInServlet() {
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
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		LogInDao logindao=new LogInDao();
		Connection con=null;
		try {
			con = logindao.getConnection();
			con.setAutoCommit(false);
			
			PreparedStatement pstm1 = con.prepareStatement("select *from sbilogin where id=?");
			pstm1.setInt(1, id);
			ResultSet rs1 = pstm1.executeQuery();
			if(!(rs1.next())){
				PreparedStatement pstm2 = con.prepareStatement("insert into sbilogin values (?,?,?)");
				pstm2.setInt(1, id);
				pstm2.setString(2, name);
				pstm2.setString(3, pwd);
				pstm2.execute();
				con.commit();
				con.close();
				response.getWriter().println("<h4 style='color:red;'>LogIn Account Created Successfully and Please Create Bank Account aslo...</h4>");
				request.getRequestDispatcher("signUpLogIn.jsp").include(request, response);
			}
			else {
				con.close();
				response.getWriter().println("<h4 style='color:red;'>Failed to Create account with Same User-Id Try another one..</h4>");
				request.getRequestDispatcher("signUpLogIn.jsp").include(request, response);
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
