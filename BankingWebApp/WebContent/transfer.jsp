<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transfer-Page</title>
</head>
<body style="background-image:url(sbi1.jpg);">
<%@ include file="header.jsp" %>
<script type="text/javascript">
	function validateForm(){
	 var tfacnum=document.transferform.tfacnum.value;
	 var transfer=document.transferform.transfer.value;
	 
	 if(tfacnum=='' || transfer==''){
		 alert("Fields can't be Blank...");
		 return false;
	 }
	 else if(tfacnum.length<5 || tfacnum.length>10){
		 alert("Account Number should be in between 5 and 10 digits only...");
		 return false;
	 }
	 return true;
	}
</script>
<div style="background-color:powderblue;">
<br><br>
<h4><ol>
<form action="transferServlet" method="post" name="transferform">
<li><spam style="color:red;">*</spam>Enter-Account Number:<pre></pre><input type="text" name="tfacnum"></li><br>
<li> <spam style="color:red;">*</spam>Enter-Amount:<pre></pre><input type="text" name="transfer"></li><br>
<input type="submit" value="Transfer" onclick="return validateForm()">
<input type="reset" value="Reset">
</form>
</ol></h4>
<br><br>
</div>
<h3 style="color:red;"><a href="homePage.jsp">Home</a></h3>
<%@ include file="footer.jsp" %>
</body>
</html>