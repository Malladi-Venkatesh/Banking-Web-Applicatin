package com.sbi.banking.model;

public class AccountBean {
	
	private static int acnum;
	private static String fname;
	private static String lname;
	private static double bal;
	
	public static int getAcnum() {
		return acnum;
	}
	public  void setAcnum(int acnum) {
		AccountBean.acnum = acnum;
	}
	public  String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		AccountBean.fname = fname;
	}
	public  String getLname() {
		return lname;
	}
	public  void setLname(String lname) {
		AccountBean.lname = lname;
	}
	public  double getBal() {
		return bal;
	}
	public  void setBal(double bal) {
		AccountBean.bal = bal;
	}
	

}
