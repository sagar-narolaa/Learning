<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
       
       <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>	
       
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Login</title>
</head>
<body style="margin-top: 50px;align-self: center;border: 3px solid green; padding: 10px;">
 

<div style="margin:auto;width:50%;">
	<form action="login" modelAttribute="loginForm" method="post">
		<label>Email</label> <input type="email" placeholder="Email" class="form-control" name="email" required="required"><br><br>
		<label>Password</label> <input type="password" placeholder="Password" class="form-control" name="pwd" required="required"><br><br>
		
	 	<button style=" display: block;width: 40%;background: green;text-align: center;border-radius: 5px;color: white;font-weight: bold;line-height: 25px " type="submit" class="btn btn-success">Login</button>
	</form>
	
	<c:set var="status" value="${status}"></c:set>

	<c:if test="${status==-1}">
			<h2>New User Can Register Below</h2>
	</c:if>
	<c:if test="${status==0}">
			<h2>Wrong Email or Password</h2>
	</c:if>
	
	
	<c:set var="status" value="${signup_status}"></c:set>

	<c:if test="${status==true}">
			<h2>SignUp Successful</h2>
	</c:if>
	<c:if test="${status==false}">
			<h2>User Exist Please Login!</h2>
	</c:if>
	
		<a style=" margin-top:40px;text-decoration:none; ;display: block;width: 115px;height: 25px;background: chocolate;padding: 10px;text-align: center;border-radius: 5px;color: white;font-weight: bold;line-height: 25px;" href="<%=request.getContextPath()%>/signupPage" class="btn btn-success">Register</a>
		
		
	
<%-- 	<a style=" margin-top:40px;text-decoration:none; ;display: block;width: 115px;height: 25px;background: chocolate;padding: 10px;text-align: center;border-radius: 5px;color: white;font-weight: bold;line-height: 25px;" href="<%=request.getContextPath()%>/list" class="btn btn-success">List Of Books</a> --%>

</div>
</body>
</html>