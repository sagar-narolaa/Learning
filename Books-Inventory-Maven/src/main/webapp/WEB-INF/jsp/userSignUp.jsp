<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>        
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign Up </title>
</head>
<body style="margin-top: 50px;align-self: center">
	<form:form action="signup" method="post" modelAttribute="signUp">
		<label>First Name</label> <input type="text" placeholder="First Name" name="fname" class="form-control"  required="required"><br><br> 
		<label>Last Name</label> <input type="text" placeholder="Last Name" name="lname" class="form-control"  required="required"><br><br> 
		<label>Email</label> <input type="email" placeholder="Email" class="form-control" name="email" required="required"><br><br>
		<label>Password</label> <input type="password" placeholder="Password" class="form-control" name="pwd" required="required"><br><br>
		
	 	<button style=" display: block;width: 40%;background: green;text-align: center;border-radius: 5px;color: white;font-weight: bold;line-height: 25px " type="submit" class="btn btn-success">Sign Up</button>
	</form:form>
	
<%-- 		<a style=" margin-top:40px;text-decoration:none; ;display: block;width: 115px;height: 25px;background: chocolate;padding: 10px;text-align: center;border-radius: 5px;color: white;font-weight: bold;line-height: 25px;" href="<%=request.getContextPath()%>/signup" class="btn btn-success">Register</a>
 --%>	
	
</body>
</html>