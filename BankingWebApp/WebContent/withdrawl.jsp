<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Withdrawl-Page</title>
</head>
<body style="background-image:url(sbi1.jpg);">
<%@ include file="header.jsp" %>
<script>
function validateForm(){
	var withdrawl=document.withdrawlform.withdrawl.value;
	
	if(withdrawl.trim()==''){
		alert("Amount can't be Blank...");
		return false;
	}
	/* else if(withdrawl='0'){
		alert("Enter Valid Amount...");
		return false;
	} */
	
	return true;
}
</script>
<div style="background-color:powderblue;">
<br><br><br><br>
<form action="withdrawlServlet" method="post" name="withdrawlform">
<spam style="color:red">*</spam>Enter Amount:<pre></pre><input type="text" name="withdrawl"><br><br>
<input type="submit" value="withdrawl" onclick="return validateForm()">
<input type="reset"  value="Reset"><br><br>
</form>
<br><br><br><br>
<pre></pre>
</div>
<h3 style="color:red;"><a href="homePage.jsp">Home</a></h3>
<%@ include file="footer.jsp" %>
</body>
</html>