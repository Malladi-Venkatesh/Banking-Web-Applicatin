<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SignUp-Account-Page</title>
</head>
<body style="background-image:url(sbi1.jpg);">
<%@ include file="header.jsp" %>
<script type="text/javascript">
function validateForm(){
	var id=document.signupform.id.value;
	var acnum=document.signupform.acnum.value;
	var fname=document.signupform.fname.value;
	var lname=document.signupform.lname.value;
	var bal=document.signupform.bal.value;
	if(id=='' ||acnum=='' || fname==''|| lname=='' || bal==''){
		alert("User-Id, FName, Lname and Balance can't be Blank...");
		return false;
	}
	
	return true;
}
</script>
<div style="background-color:powderblue;">
<br>
<h3 style="color:red;">Enter All Fields...</h3>
<form action="signUpAccountServlet" method="post" name="signupform">
<ol>
<li><spam style="color:red">*</spam>User-Id(Numbers only):<pre></pre><input type="text" name="id"></li><br>
<li><spam style="color:red">*</spam>Account Number(Numbers only):<pre></pre><input type="text" name="acnum"></li><br>
<li><spam style="color:red">*</spam>FirstName:<pre></pre><input type="text" name="fname"></li><br>
<li><spam style="color:red">*</spam>LastName:<pre></pre><input type="text" name="lname"></li><br>
<li><spam style="color:red;">*</spam>Balance:<pre></pre><input type="text" name="bal"/></li></ol>
<h5 style="text-align:center;"><input type="submit" value="Submit" onclick="return validateForm()">
&nbsp&nbsp<input type="reset"  value="Reset"><br><br></h5>
</form>
<h3 style="color:red;text-align:center;"><a href="logInScreen.jsp">Home</a></h3>
<br>
</div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>