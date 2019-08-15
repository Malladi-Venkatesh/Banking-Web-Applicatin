package com.mangaraoit.junit;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.sbi.banking.dao.LogInDao;

public class TestConnection {
	
	@Test
	public void connectionTest() throws ClassNotFoundException, SQLException {
		 assertEquals(Connection.class, LogInDao.getConnection()); 
		 System.out.println(LogInDao.getConnection());
		assertEquals(1, 1);
	}
}