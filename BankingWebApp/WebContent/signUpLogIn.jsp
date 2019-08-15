<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SignUp-LogInAccount-Page</title>
</head>
<body style="background-image:url(sbi1.jpg);">
<%@ include file="header.jsp" %>
<script type="text/javascript">
function validateForm(){
	var id=document.signupform.id.value;
	var name=document.signupform.name.value;
	var pw=document.signupform.pwd.value;
	if(id=='' || pw==''|| name==''){
		alert("User-Id, Name and Password can't be Blank...");
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
<br>
<h3 style="color:red;">Enter All Fields...</h3>
<form action="signUpLogInServlet" method="post" name="signupform">
<ol>
<li><spam style="color:red">*</spam>User-Id(Numbers only):<pre></pre><input type="text" name="id"></li><br>
<li><spam style="color:red">*</spam>User-Name:<pre></pre><input type="text" name="name"></li><br>
<li><spam style="color:red;">*</spam>Password:<pre></pre><input type="text" name="pwd"/></li></ol>
<h5 style="text-align:center;"><input type="submit" value="Submit" onclick="return validateForm()">
<input type="reset"  value="Reset"><br><br></h5>
</form>
<form action="signUpAccount.jsp" method="post">
<input type="submit" value="SignUp-">
<h4 style="color:red;">To Create Bank Account </h4>
</form>
<h3 style="color:red;text-align:center;"><a href="logInScreen.jsp">Home</a></h3>
<br>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>