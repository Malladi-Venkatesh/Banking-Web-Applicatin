<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Deposit-Page</title>
</head>
<body style="background-image:url(sbi1.jpg);">
<%@ include file="header.jsp" %>
<script>
function validateForm(){
	var deposit=document.depositform.deposit.value;
	
	if(deposit==''){
		alert("Amount can't be Blank...");
		return false;
	}
	
	return true;
}
</script>
<div style="background-color:powderblue;">
<br><br><br><br>
<form action="depositServlet" method="post" name="depositform">
<spam style="color:red">*</spam>Enter Amount:<pre></pre><input type="text" name="deposit"><br><br>
<input type="submit" value="Deposit" onclick="return validateForm()">
<input type="reset"  value="Reset"><br><br>
</form>
<br><br><br><br>
<pre></pre>
</div>
<h3 style="color:red;"><a href="homePage.jsp">Home</a></h3>
<%@ include file="footer.jsp" %>
</body>
</html>