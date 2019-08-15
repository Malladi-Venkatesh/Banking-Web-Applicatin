<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.sbi.banking.model.AccountBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CheckBalace-Page</title>
</head>
<body style="background-image:url(sbi1.jpg);">
<%@ include file="header.jsp" %>
<%
AccountBean accountbean=(AccountBean) request.getAttribute("accountbean");
%>
<div style="background-color:powderblue;">
<br><br>
<h3 style="color:red;">
Your Account Details are:
</h3>
<ol>
<h4>
<li>Account Number : <%=accountbean.getAcnum() %></li>
<li>Account Holder Name : <%=accountbean.getFname()+" "+accountbean.getLname()%>
</li>
<li>Account Balance : Rs. <%=accountbean.getBal() %>0/-</li>
</ol>
<br><br><br><br><br>
</div>
<h3 style="color:red;"><a href="homePage.jsp">Home</a></h3>
<%@ include file="footer.jsp" %>
</body>