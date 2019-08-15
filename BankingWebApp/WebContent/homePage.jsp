<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="loginbean" class="com.sbi.banking.model.LogInBean" scope="request"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home-Page</title>
</head>
<body style="background-image:url(sbi1.jpg);">
<%@ include file="header.jsp" %>
<div style="background-color:powderblue;">
<h3>
Welcome Mr./Mrs. <%=session.getAttribute("name") %>
</h3>
<ol>
<h3>
<li><a href="checkBalanceServlet">Check Balance</a></li>
<li><a href="deposit.jsp">Deposit</a></li>
<li><a href="withdrawl.jsp">Withdraw</a></li>
<li><a href="transfer.jsp">Transfer</a></li></h3>
</ol>
<form action="exitServlet">
<h3 style="text-align:center;color:red;"><input type="submit" value="Log-Out" style="color:red;"></h3>
</form>
<br><br><br><br><br>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>