<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LogIn-Page</title>
</head>
<body style="background-image:url(sbi1.jpg);">
<%@ include file="header.jsp" %>
<script>
function validateForm(){
	var id=document.loginform.id.value;
	var pw=document.loginform.pwd.value;
	if(id=='' || pw==''){
		alert("User-Id and Password can't be Blank...");
		return false;
	}
	else if((id.length<5) || (id.length>10)){
		alert("User-Id should be in between 5 to 10 characters...");
		return false;
	}
	else if((pw.length<4) || (pw.length>10)){
		alert("Password should be in betweenn 4 to 10 characters...");
		return false;
	}
	return true;
}
</script>
<div style="background-color:powderblue;">
<br><br><br><br>
<form action="loginservlet" method="post" name="loginform">
<spam style="color:red">*</spam>User-Id:<pre></pre><input type="text" name="id"><br><br>
<spam style="color:red;">*</spam>Password:<pre></pre><input type="text" name="pwd"/><br><br>
<input type="submit" value="LogIn" onclick="return validateForm()">
<input type="reset"  value="Reset"><br><br>
</form>
<form action="signUpLogIn.jsp" method="post">
<input type="submit" value="SignUp-LogIn">
<h4 style="color:red;">To Create LogIn Account </h4>
</form>
<br><br><br><br>
<pre></pre>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>