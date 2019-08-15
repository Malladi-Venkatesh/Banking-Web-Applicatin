package com.sbi.banking.bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sbi.banking.dao.LogInDao;
import com.sbi.banking.model.LogInBean;

public class LogInBo {

	public boolean validate(LogInBean loginbean, HttpServletRequest request) throws ClassNotFoundException, SQLException {
		LogInDao logindao=new LogInDao();
		HttpSession session = request.getSession();
		Connection con=logindao.getConnection();
		PreparedStatement pstm1 = con.prepareStatement("select *from sbilogin where id=? and password=?");
		pstm1.setLong(1, loginbean.getId());
		pstm1.setString(2, loginbean.getPassword());
		ResultSet rs1 = pstm1.executeQuery();
		if(rs1.next()) {
			// This is for to display user name from login table
			String name=rs1.getString(2);
			session.setAttribute("name", name);
			// This is for to get details (check balance) from account table by using login variable
			PreparedStatement pstm2=con.prepareStatement("select *from sbiaccount where id=?");
			pstm2.setLong(1, loginbean.getId());
			ResultSet rs2 = pstm2.executeQuery();
			if(rs2.next()) {
				int acnum=rs2.getInt(1);
				//For entire Session This attribute is useful ...
				session.setAttribute("acnum", acnum);
			}
			con.close();
			return true;
		}
		con.close();
		return false;
	}
	
}
