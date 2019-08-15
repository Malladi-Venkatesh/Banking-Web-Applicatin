package com.sbi.banking.controller;

import com.sbi.banking.dao.LogInDao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

/**
 * Servlet implementation class TransferServlet
 */
@WebServlet(name = "transferServlet", urlPatterns = { "/transferServlet" })
public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TransferServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int acnum = (int) session.getAttribute("acnum");
		String tfac = request.getParameter("tfacnum");
		int tfacnum = Integer.parseInt(tfac);
		String tfam = request.getParameter("transfer");
		double tfamnt = Double.parseDouble(tfam);
		LogInDao logindao = new LogInDao();
		Connection con = null;

		try {
			con = logindao.getConnection();
			con.setAutoCommit(false);
			// cheking for Transfer account is weather it is in Database or not
			PreparedStatement pst = con.prepareStatement("select *from sbiaccount where acnum=?");
			pst.setInt(1, tfacnum);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				// cheking for sufficient balance...
				PreparedStatement pstm = con.prepareStatement("select *from sbiaccount where acnum=?");
				pstm.setInt(1, acnum);
				ResultSet rs1 = pstm.executeQuery();
				if(rs1.next()) {
					if (tfamnt <= rs1.getDouble(5)) {
						// Crediting Amount to Another(Transfer) Account...
						PreparedStatement pstm1 = con
								.prepareStatement("update sbiaccount set balance=balance+? where acnum=?");
						pstm1.setDouble(1, tfamnt);
						pstm1.setInt(2, tfacnum);
						pstm1.executeQuery();
						// Debiting Amount from Your Account...
						PreparedStatement pstm2 = con
								.prepareStatement("update sbiaccount set balance=balance-? where acnum=?");
						pstm2.setDouble(1, tfamnt);
						pstm2.setInt(2, acnum);
						pstm2.executeQuery();
						con.commit();
						con.close();
						response.getWriter().println("<h3 style='color:red;'>Rs. " + tfamnt + "0/- is Transferred to the Account:"
								+ tfacnum + " successfully...</h3>");
						request.getRequestDispatcher("homePage.jsp").include(request, response);
					} else if (tfamnt == 0) {
						response.getWriter().println("<h3 style='color:red;'>You have Entered InValid Amount..</h3>");
						request.getRequestDispatcher("transfer.jsp").include(request, response);
					}
					else {
						response.getWriter().println("<h3 style='color:red;'>You have Insufficient Balance to send Rs. "
					+tfamnt+"0/- To "+tfacnum+"...</h3>");
						request.getRequestDispatcher("transfer.jsp").include(request, response);
					}
				}
			}
			else {
				response.getWriter().println("<h3 style='color:red;'>You have Entered Wrong Account Number : "+tfacnum +" </h3>");
				request.getRequestDispatcher("transfer.jsp").include(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			try {
				con.rollback();
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

}
