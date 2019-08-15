package com.sbi.banking.model;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.sbi.banking.bo.LogInBo;

public class LogInBean {

	private long id;
	private String pwd;

	public void setId(long id) {
		this.id=id;
		
	}
	public long getId() {
		return id;
	}
	public void setPassword(String pwd) {
		this.pwd=pwd;
	}
	public String getPassword() {
		return pwd;
	}
	public boolean validate(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		LogInBo loginbo=new LogInBo();
		return loginbo.validate(this, request);
	}

}
